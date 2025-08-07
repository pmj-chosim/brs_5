package com.example.demo.repository.entity.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentMethod {
    CARD("신용카드"),
    PHONE("핸드폰결제"),
    KAKAO_PAY("카카오페이"),
    NAVER_PAY("네이버페이"),
    TRANS("계좌이체"),
    VBANK("가상계좌");

    private final String description;

}
