package com.example.demo.message;

import com.example.demo.message.event.OrderPaidEvent;
import com.example.demo.repository.entity.Order;
import com.example.demo.repository.ports.IOrderRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShippingEventListener {
    private final IOrderRepositoryPort orderRepository;

    @Async
    @EventListener
    public void handleOrderPaidEvent(OrderPaidEvent event) {
        log.info("결제 완료 이벤트 수신! OrderId: {}, 배송 프로세스를 시작합니다.", event.getOrderId());
        try {
            Thread.sleep(1000); // 외부 시스템 연동 시뮬레이션
            Order order = orderRepository.findById(event.getOrderId()).orElseThrow();
            order.startShipping();
            orderRepository.save(order);
            log.info("OrderId: {} 배송 준비(SHIPPING_READY) 상태로 변경 완료.", event.getOrderId());
        } catch (Exception e) {
            log.error("배송 처리 중 오류 발생", e);
        }
    }
}
