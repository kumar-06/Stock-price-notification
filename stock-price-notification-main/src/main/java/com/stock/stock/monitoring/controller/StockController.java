package com.stock.stock.monitoring.controller;

import com.stock.stock.monitoring.entity.StockData;
import com.stock.stock.monitoring.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class StockController {

    private final StockService stockService;

    @PostMapping("/stock/save/{symbol}")
    public ResponseEntity<StockData> savStockBySymbol(@PathVariable String symbol) {
        return ResponseEntity.ok(stockService.saveStockBySymbol(symbol));
    }
}
