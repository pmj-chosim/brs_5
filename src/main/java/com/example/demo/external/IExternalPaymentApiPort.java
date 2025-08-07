package com.example.demo.external;

public class IExternalPaymentApiPort {
    boolean processPayment(Order order, PaymentMethod paymentMethod);
}
