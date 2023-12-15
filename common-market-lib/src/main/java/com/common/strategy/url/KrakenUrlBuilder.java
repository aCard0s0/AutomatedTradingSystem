package com.common.strategy.url;

import java.sql.Timestamp;

public class KrakenUrlBuilder implements UrlStrategy {
    @Override
    public String buildCandlestickUrl(String pair, String interval) {
        return "https://api.kraken.com/0/public/OHLC?pair=" + pair + "&interval=" + interval;
    }

    @Override
    public String buildCandlestickUrl(String pair, String interval, Timestamp start) {
        return null;
    }

    @Override
    public String buildCandlestickUrl(String pair, String interval, Timestamp start, Timestamp end) {
        return "https://api.kraken.com/0/public/OHLC?pair=" + pair + "&interval=" + interval;
    }

    @Override
    public String buildWebSocketUrl() {
        return null;
    }
}
