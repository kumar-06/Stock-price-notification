package com.stock.stock.monitoring.service.impl;

import com.stock.stock.monitoring.dto.SubscriptionRequest;
import com.stock.stock.monitoring.entity.Subscription;
import com.stock.stock.monitoring.entity.User;
import com.stock.stock.monitoring.reository.SubscriptionRepository;
import com.stock.stock.monitoring.reository.UserRepository;
import com.stock.stock.monitoring.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    @Override
    public Subscription addSubscription(SubscriptionRequest subscriptionRequest) {
        Subscription subscription = new Subscription();
        User user = userRepository.findByUsername(subscriptionRequest.getUsername()).orElseThrow(() -> new RuntimeException());
        subscription.setSymbol(subscriptionRequest.getSymbol());
        subscription.setUser(user);
        return subscriptionRepository.save(subscription);
    }
}
