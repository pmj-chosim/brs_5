package com.example.demo.message.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderPaidEvent {
    private final Long orderId;
}
