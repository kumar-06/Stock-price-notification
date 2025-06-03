package com.stock.stock.monitoring.service.impl;

import com.stock.stock.monitoring.client.StockClient;
import com.stock.stock.monitoring.dto.NotificationEvent;
import com.stock.stock.monitoring.dto.StockResponse;
import com.stock.stock.monitoring.dto.StockUpdateEvent;
import com.stock.stock.monitoring.entity.StockData;
import com.stock.stock.monitoring.entity.Subscription;
import com.stock.stock.monitoring.exception.ApplicationException;
import com.stock.stock.monitoring.exception.ErrorCode;
import com.stock.stock.monitoring.reository.StockRepository;
import com.stock.stock.monitoring.reository.SubscriptionRepository;
import com.stock.stock.monitoring.service.RedisService;
import com.stock.stock.monitoring.service.StockService;
import com.stock.stock.monitoring.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StockServiceImpl implements StockService {

    private final StockClient stockClient;
    private final StockRepository stockRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final AmqpTemplate amqpTemplate;
    private final RedisService redisService;
  
    @Override
    public StockData saveStockBySymbol(String symbol) {
        try {
            Object stockDataResponse = stockClient.getStockData("TIME_SERIES_DAILY", symbol, "demo");
            if (Objects.isNull(stockDataResponse))
                throw new ApplicationException(ErrorCode.BAD_REQUEST);
            StockResponse stockResponse = MapperUtil.mapper(stockDataResponse, StockResponse.class);
            StockResponse.TimeSeriesData timeSeriesData = stockResponse.getTimeSeriesDaily().values().stream().findFirst().get();
            timeSeriesData.getClose();
            StockData stockData = new StockData();
            stockData.setPrice(Double.valueOf(timeSeriesData.getClose()));
            stockData.setSymbol(symbol);
            stockData.setTimestamp(LocalDateTime.now());
            return stockRepository.save(stockData);
        } catch (Exception e) {
            log.error("[saveStockBySymbol] Exception while fetching the data from stock client for symbol-{}", symbol, e);
            throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public void processStockUpdateEvent(StockUpdateEvent stockUpdateEvent) {
        Optional<StockData> stockDataOp = stockRepository.findBySymbol(stockUpdateEvent.getSymbol());
        if (stockDataOp.isEmpty()) {
            log.warn("[processStockUpdateEvent] stock data not present for symbol-{}", stockUpdateEvent.getSymbol());
        }
        stockDataOp.get().setPrice(stockUpdateEvent.getPrice());
        stockRepository.save(stockDataOp.get());
        List<Subscription> subscriptions = subscriptionRepository.findBySymbol(stockUpdateEvent.getSymbol());
        notifyUsers(subscriptions, stockUpdateEvent.getPrice());

    }

    private void notifyUsers(List<Subscription> subscriptions, Double price) {
        subscriptions.stream().filter(subscription -> {
            Boolean isNotificationSenInLast1hr = redisService.findByKey(subscription.getUser().getEmail(), "notif", Boolean.class);
            return Objects.isNull(isNotificationSenInLast1hr) || !isNotificationSenInLast1hr;
        }).forEach( subscription -> pushEvent(subscription, price));
    }

    private void pushEvent(Subscription subscription, Double price) {
        NotificationEvent notificationEvent = NotificationEvent.builder()
                .to(subscription.getUser().getEmail())
                .price(price)
                .symbol(subscription.getSymbol())
                .build();
        amqpTemplate.convertAndSend("notification-topic", "notification-routing", notificationEvent);
        log.info("[pushEvent] notification event is sent-{}", notificationEvent);
        redisService.save(subscription.getUser().getEmail(), "notif", 60, true);
    }
}
