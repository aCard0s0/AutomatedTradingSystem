package com.common.factory;

import com.common.model.Market;
import com.common.enums.Exchange;
import com.common.enums.Timeframe;
import com.common.enums.Pair;
import com.common.strategy.timeframe.BinanceTimeframe;
import com.common.strategy.timeframe.BitstampTimeframe;
import com.common.strategy.timeframe.KrakenTimeframe;
import com.common.strategy.pair.BinancePair;
import com.common.strategy.pair.BitstampPair;
import com.common.strategy.pair.KrakenPair;
import com.common.strategy.url.BinanceUrlBuilder;
import com.common.strategy.url.BitstampUrlBuilder;
import com.common.strategy.url.KrakenUrlBuilder;

public class MarketFactory {
    public static Market buildMarket(Exchange exchange, Pair pair, Timeframe timeframe) {
        return switch (exchange) {
            case BINANCE -> new Market(exchange, new BinancePair(pair), new BinanceTimeframe(timeframe), new BinanceUrlBuilder());
            case BITSTAMP -> new Market(exchange, new BitstampPair(pair), new BitstampTimeframe(timeframe), new BitstampUrlBuilder());
            case KRAKEN -> new Market(exchange, new KrakenPair(pair), new KrakenTimeframe(timeframe), new KrakenUrlBuilder());
        };
    }
}
