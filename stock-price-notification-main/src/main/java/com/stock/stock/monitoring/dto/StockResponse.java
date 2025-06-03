package com.stock.stock.monitoring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class StockResponse {
    @JsonProperty("Time Series (Daily)")
    public Map<String, TimeSeriesData> timeSeriesDaily;


//    @Data
//    @NoArgsConstructor
//    public static class MetaData {
//        public String information;
//        public String symbol;
//        public String lastRefreshed;
//        public String outputSize;
//        public String timeZone;
//    }

    @Data
    @NoArgsConstructor
    public static class TimeSeriesData {
        public String open;
        public String high;
        public String low;
        @JsonProperty("4. close")
        public String close;
        public String volume;
    }
}