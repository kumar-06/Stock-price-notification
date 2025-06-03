package com.stock.stock.monitoring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationEvent {

    private String to;
    private String symbol;
    private Double price;
}
