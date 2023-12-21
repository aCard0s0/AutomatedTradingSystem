package com.common.strategy.url;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record BinanceUrlBuilder() implements UrlStrategy {
    @Override
    public String buildCandlestickUrl(String pair, String interval) {
        return null;
    }

    @Override
    public String buildCandlestickUrl(String pair, String interval, LocalDateTime start) {
        return null;
    }

    @Override
    public String buildCandlestickUrl(String pair, String interval, LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public String buildWebSocketUrl() {
        return null;
    }
}
