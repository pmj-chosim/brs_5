package com.example.demo.external;

import com.example.demo.repository.entity.vo.PaymentMethod;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TossPaymentApi extends IExternalPaymentApiPort {

    @Override
    boolean processPayment(Order order, PaymentMethod paymentMethod) {
        log.info("OrderId: {}에 대한 결제 요청. 금액 {}, 결제 수단 [}", order.getId(), order.getDiscountedPrice(), paymentMethod);
        if (paymentMethod == PaymentMethod.VBANK || paymentMethod == PaymentMethod.TRANS) {
            log.info("가상계좌 발급 또는 입금 대기 상태입니다.");
            return false;
        }
        log.info("결제가 성공적으로 완료되었습니다.");
        return true;
    }
}
