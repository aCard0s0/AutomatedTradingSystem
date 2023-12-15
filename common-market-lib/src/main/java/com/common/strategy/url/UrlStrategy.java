package com.common.strategy.url;

import java.sql.Timestamp;

public interface UrlStrategy {
    String buildCandlestickUrl(String pair, String interval);
    String buildCandlestickUrl(String pair, String interval, Timestamp start);
    String buildCandlestickUrl(String pair, String interval, Timestamp start, Timestamp end);

    String buildWebSocketUrl();

}
