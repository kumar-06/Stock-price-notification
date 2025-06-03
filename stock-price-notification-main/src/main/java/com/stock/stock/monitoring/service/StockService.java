package com.stock.stock.monitoring.service;

import com.stock.stock.monitoring.dto.StockUpdateEvent;
import com.stock.stock.monitoring.entity.StockData;

public interface StockService {
    StockData saveStockBySymbol(String symbol);

    void processStockUpdateEvent(StockUpdateEvent stockUpdateEvent);
}
