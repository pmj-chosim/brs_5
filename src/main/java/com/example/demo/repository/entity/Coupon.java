package com.example.demo.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Coupon extends AggregateRoot {
    private Long id;
    private String name;
    private long discountAmount;

    public long calculateDiscount(long originalPrice){
        return Math.min(originalPrice, discountAmount);
    }
}
