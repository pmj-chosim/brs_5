package com.example.demo.service.usecases;

import com.example.demo.repository.entity.Coupon;
import com.example.demo.repository.entity.Order;
import com.example.demo.repository.entity.vo.PaymentMethod;
import com.example.demo.repository.entity.vo.ShippingInfo;
import com.example.demo.service.IInputPort;

import java.util.List;

public interface IPurchaseOrderUseCase extends IInputPort {
    Order process(Long customerId, ShippingInfo shippingInfo, PaymentMethod paymentMethod, Coupon coupon);
    List<Order> getOrders(Long customerId);
    Order getOrderById(Long orderId);
}
