package com.mws.utils;

import com.cml.enums.Exchange;
import com.cml.enums.Pair;
import com.cml.enums.Timeframe;
import com.cml.factory.MarketFactory;
import com.cml.model.Market;

public class MarketMockGenerator {
    public static Market buildMockMarket() {
        return MarketFactory.buildMarket(Exchange.KRAKEN, Pair.EUR_BTC, Timeframe.ONE_DAY);
    }

    public static Market buildMockMarketWith(Exchange exchange, Pair pair, Timeframe timeframe) {
        return MarketFactory.buildMarket(exchange, pair, timeframe);
    }

    public static Market buildMockMarketWith(Timeframe timeframe) {
        return MarketFactory.buildMarket(Exchange.KRAKEN, Pair.EUR_BTC, timeframe);
    }
}
