package com.example.demo.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventPublisher implements IMessagePublisherPort {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publish(Object event) {
        log.info("도메인 이벤트 발행: {}", event.getClass().getSimpleName());
        eventPublisher.publishEvent(event);
    }
}
