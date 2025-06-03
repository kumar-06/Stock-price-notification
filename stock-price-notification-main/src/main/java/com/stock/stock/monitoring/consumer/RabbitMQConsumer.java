package com.stock.stock.monitoring.consumer;

import com.stock.stock.monitoring.config.RabbitMQConfig;
import com.stock.stock.monitoring.dto.StockUpdateEvent;
import com.stock.stock.monitoring.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQConsumer {

    private final StockService stockService;

    @Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 5000))
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void stockUpdateEventConsumer(StockUpdateEvent stockUpdateEvent) {
        try {
            log.info("[stockUpdateEventConsumer] Received message-{}", stockUpdateEvent);
            stockService.processStockUpdateEvent(stockUpdateEvent);
        } catch (Exception e) {
            log.error("[stockUpdateEventConsumer] Exception while processing stock update event-{}", stockUpdateEvent, e);
            throw e;
        }
    }
}