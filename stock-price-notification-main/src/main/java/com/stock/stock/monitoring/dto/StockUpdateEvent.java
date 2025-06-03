package com.stock.stock.monitoring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockUpdateEvent {

    private String symbol;
    private Double price;
}
