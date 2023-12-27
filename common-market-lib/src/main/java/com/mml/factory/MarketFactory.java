package com.mml.factory;

import com.mml.model.Market;
import com.mml.enums.Exchange;
import com.mml.enums.Timeframe;
import com.mml.enums.Pair;
import com.mml.strategy.timeframe.BinanceTimeframe;
import com.mml.strategy.timeframe.BitstampTimeframe;
import com.mml.strategy.timeframe.KrakenTimeframe;
import com.mml.strategy.pair.BinancePair;
import com.mml.strategy.pair.BitstampPair;
import com.mml.strategy.pair.KrakenPair;
import com.mml.strategy.url.BinanceUrlBuilder;
import com.mml.strategy.url.BitstampUrlBuilder;
import com.mml.strategy.url.KrakenUrlBuilder;

public class MarketFactory {
    public static Market buildMarket(Exchange exchange, Pair pair, Timeframe timeframe) {
        return switch (exchange) {
            case BINANCE -> new Market(exchange, new BinancePair(pair), new BinanceTimeframe(timeframe), new BinanceUrlBuilder());
            case BITSTAMP -> new Market(exchange, new BitstampPair(pair), new BitstampTimeframe(timeframe), new BitstampUrlBuilder());
            case KRAKEN -> new Market(exchange, new KrakenPair(pair), new KrakenTimeframe(timeframe), new KrakenUrlBuilder());
        };
    }
}
