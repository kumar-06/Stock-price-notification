package com.stock.stock.monitoring.dto;

import lombok.Data;

@Data
public class SubscriptionRequest {
    private String username;
    private String symbol;
}
