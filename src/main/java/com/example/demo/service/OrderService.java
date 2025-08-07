package com.example.demo.service;

import com.example.demo.external.IExternalPaymentApiPort;
import com.example.demo.repository.entity.Cart;
import com.example.demo.repository.entity.Coupon;
import com.example.demo.repository.entity.Order;
import com.example.demo.repository.entity.Product;
import com.example.demo.repository.entity.vo.OrderItem;
import com.example.demo.repository.entity.vo.PaymentMethod;
import com.example.demo.repository.entity.vo.ShippingInfo;
import com.example.demo.repository.ports.ICartRepositoryPort;
import com.example.demo.repository.ports.IOrderRepositoryPort;
import com.example.demo.repository.ports.IProductRepositoryPort;
import com.example.demo.service.usecases.IManageCartUseCase;
import com.example.demo.service.usecases.IPurchaseOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IPurchaseOrderUseCase {

    private final IProductRepositoryPort productRepository;
    private final IOrderRepositoryPort orderRepository;
    private final ICartRepositoryPort cartRepository;

    private final IExternalPaymentApiPort paymentApi;
    private final IManageCartUseCase eventPublisher;
    @Override
    public Order process(Long customerId, ShippingInfo shippingInfo, PaymentMethod paymentMethod, Coupon coupon) {
        Cart cart=cartRepository.findByCustomerId(customerId).orElseThrow();
        List<OrderItem> orderItems = cart.getItems().stream()
                .map(item->new OrderItem(
                        item.getProductId(),
                        item.getOptionId(),
                        item.getProductName(),
                        item.getOptionName(),
                        item.getQuantity(),
                        item.getPrice())
                ).collect(Collectors.toList());
        for (OrderItem orderItem : orderItems){
            Product product =productRepository.findById(orderItem.getProductId()
                    .orElseThrow(()->new IllegalArgumentException(("상품을 찾을 수 없습니다. ID: "+orderItem.getProductId()));
            if(!product.isAvailable(orderItem.getOptionId(), orderItem.getQuantity())){
                throw new IllegalStateException("상품의 재고가 부족합니다"+product.getName());
            }
        }

        Order order=new Order(customerId, orderItems, shippingInfo);
        order.applyCoupon(coupon);
        orderRepository.save(order);

        boolean paymentSucess=paymentApi.processPayment(order, paymentMethod);

        if(paymentSucess){
            orderItems.forEach(line->{
                Product product= productRespository.findById(line.getProductId()).get();
                product.decreaseStock(line.getOptionId(), line.getQuantity());
                productRepository.save(product);
            });

            order.paymentComplete();
            Order saveOrder = orderRepository.save(order);
            saveOrder.getDomainEvents().forEach(eventPublisher::publish);
            saveOrder.clearDomainEvents();
        }

        cartRepository.findByOptionalId(customerId)
                .ifPresentOrElse(completedCart ->{
                    completedCart.getItems().clear();
                    cartRepository.save(completedCart);
                });
        return order;
    }

    @Override
    public List<Order> getOrders(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(()-> new IllegalArgumentException("주문을 찾을 수 없습니다"));
    }
}
