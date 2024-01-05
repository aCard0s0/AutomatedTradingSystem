package com.cml.strategy.url;

import java.time.LocalDateTime;

public record BitstampUrlBuilder() implements UrlStrategy {
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
