package com.example.demo.repository.entity.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ShippingInfo {
    private final String recipient;
    private final String address;
    private final String phone;
}
