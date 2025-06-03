package com.stock.stock.monitoring.service;

import com.stock.stock.monitoring.dto.SubscriptionRequest;
import com.stock.stock.monitoring.entity.Subscription;

public interface SubscriptionService {
    Subscription addSubscription(SubscriptionRequest subscriptionRequest);
}
