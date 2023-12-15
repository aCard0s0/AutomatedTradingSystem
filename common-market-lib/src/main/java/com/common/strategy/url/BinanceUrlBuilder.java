package com.common.strategy.url;

import java.sql.Timestamp;

public record BinanceUrlBuilder() implements UrlStrategy {
    @Override
    public String buildCandlestickUrl(String pair, String interval) {
        return null;
    }

    @Override
    public String buildCandlestickUrl(String pair, String interval, Timestamp start) {
        return null;
    }

    @Override
    public String buildCandlestickUrl(String pair, String interval, Timestamp start, Timestamp end) {
        return null;
    }

    @Override
    public String buildWebSocketUrl() {
        return null;
    }
}
