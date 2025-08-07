package com.example.demo.repository.entity;


import com.example.demo.repository.entity.vo.CartItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@ToString
public class Cart implements AggregateRoot{
    @Setter
    private Long id;
    private final long customerId;
    private final List<CarItem> items =new ArrayList<>();

    public Cart(Long customerId){
        this.customerId =customerId;
    }

    public void addProduct(Product product, ProductOption option, int quantity){
        if (option.getStock() < quantity){
            throw new IllegalStateException("상품의 재고가 부족하여 장바구니에 담을 수 없습니다.");
        }
        items.stream()
                .filter(item -> item.getProductId().equals(product.getId())&&item.getOptionId().equals(option.getId()))
                .findFirst()
                .ifPresentOrElse(
                        item -> item.increaseQuantity(quantity),
                        () -> items.add(new CartItem(product, option, quantity))
                );
    }

    public long calculateTotalPrice(){
        return items.stream().mapToLong(CartItem::calculatePrice).sum();
    }

}
