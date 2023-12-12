package com.common.stategy.url;

public record BinanceUrlBuilder() implements UrlStrategy {
    @Override
    public String buildCandlestickUrl(String pair, String interval) {
        return null;
    }

    @Override
    public String buildWebSocketUrl() {
        return null;
    }
}
