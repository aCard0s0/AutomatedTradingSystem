package com.common.stategy.url;

public interface UrlStrategy {
    String buildCandlestickUrl(String pair, String interval);

    String buildWebSocketUrl();

}
