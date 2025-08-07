package com.example.demo.repository.entity.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderItem {
    private final Long productId;
    private final Long optionId;
    private final String productName;
    private final String optionName;
    private final int quantity;
    private final long price;

    public long calculatePrice(){return price*quantity;}
}
