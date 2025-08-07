package com.example.demo.repository.entity.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    PAYMENT_READY("결제대기"),
    PAYMENT_COMPLETE("결제완료"),
    SHIPPING_READY("배송준비"),
    SHIPPING("배송중"),
    SHIPPING_COMPLETE("배송완료"),
    PURCHASE_COMPLETE("구매확정");

    private final String description;

}
