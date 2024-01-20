package com.mws.utils;

import com.mws.domain.MarketEntity;

public class MarketTableMockGenerator {
    public static MarketEntity buildMockMarketTableWith(long id, boolean isActive) {
        return new MarketEntity(id, "DummyExchange", "DummyPair", "DummyTimeframe", isActive);
    }

    public static MarketEntity buildMockMarketTableWith(long id, String exchange, String pair, String timeframe, boolean isActive) {
        return new MarketEntity(id, exchange, pair, timeframe, isActive);
    }
}
