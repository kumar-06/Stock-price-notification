package com.stock.stock.monitoring.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stock-client", url = "https://www.alphavantage.co")
public interface StockClient {

    @GetMapping("/query")
    Object getStockData(@RequestParam String function,
                        @RequestParam String symbol,
                        @RequestParam String apikey);
}
