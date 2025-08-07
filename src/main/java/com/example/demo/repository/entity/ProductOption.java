package com.example.demo.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ProductOption {
    private Long id;
    private final String name;
    private final long price;
    private int stock;

    public void decreaseStock(int quantity){this.stock-=quantity;}
}
