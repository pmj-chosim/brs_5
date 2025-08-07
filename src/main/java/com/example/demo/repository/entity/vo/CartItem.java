package com.example.demo.repository.entity.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CartItem {
    private final Long productId;
    private final String productName;
    private final Long optionId;
    private final String OptionName;
    private final long price;
    private int quantity;

    public CartItem(Product product, ProductOption option, int quantity){
        this.productId=product.getId();
        this.productName=product.getName();
        this.optionId=option.getId();
        this.optionName=option.getName();
        this.price=option.getPrice();
        this.quantity=quantity;
    }

    public void increaseQuantity(int quantity){
        this.quantity+=quantity;
    }

    public long calculatePrice(){
        return price*quantity;
    }
}
