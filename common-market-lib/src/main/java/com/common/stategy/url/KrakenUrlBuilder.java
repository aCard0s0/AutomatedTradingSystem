package com.common.stategy.url;

public class KrakenUrlBuilder implements UrlStrategy {
    @Override
    public String buildCandlestickUrl(String pair, String interval) {
        return "https://api.kraken.com/0/public/OHLC?pair=" + pair + "&interval=" + interval;
    }

    @Override
    public String buildWebSocketUrl() {
        return null;
    }
}
