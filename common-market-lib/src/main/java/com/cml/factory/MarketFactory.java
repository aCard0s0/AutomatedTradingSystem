package com.cml.factory;

import com.cml.model.Market;
import com.cml.enums.Exchange;
import com.cml.enums.Timeframe;
import com.cml.enums.Pair;
import com.cml.strategy.timeframe.BinanceTimeframe;
import com.cml.strategy.timeframe.BitstampTimeframe;
import com.cml.strategy.timeframe.KrakenTimeframe;
import com.cml.strategy.pair.BinancePair;
import com.cml.strategy.pair.BitstampPair;
import com.cml.strategy.pair.KrakenPair;
import com.cml.strategy.url.BinanceUrlBuilder;
import com.cml.strategy.url.BitstampUrlBuilder;
import com.cml.strategy.url.KrakenUrlBuilder;

import static com.google.common.base.Preconditions.checkArgument;

public class MarketFactory {
    public static Market buildMarket(Exchange exchange, Pair pair, Timeframe timeframe) {
        if (exchange == null) {
            throw new IllegalArgumentException("Exchange cannot be null");
        }
        return switch (exchange) {
            case BINANCE -> new Market(exchange, new BinancePair(pair), new BinanceTimeframe(timeframe), new BinanceUrlBuilder());
            case BITSTAMP -> new Market(exchange, new BitstampPair(pair), new BitstampTimeframe(timeframe), new BitstampUrlBuilder());
            case KRAKEN -> new Market(exchange, new KrakenPair(pair), new KrakenTimeframe(timeframe), new KrakenUrlBuilder());
        };
    }

    public static Market buildMarket(String exchange, String pair, String timeframe) {
        return buildMarket(Exchange.fromString(exchange), Pair.fromString(pair), Timeframe.fromString(timeframe));
    }
    
}
