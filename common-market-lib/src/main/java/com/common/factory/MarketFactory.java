package com.common.factory;

import com.common.model.Market;
import com.common.enums.Exchange;
import com.common.enums.Timeframe;
import com.common.enums.Pair;
import com.common.stategy.interval.BinanceTimeframe;
import com.common.stategy.interval.BitstampTimeframe;
import com.common.stategy.interval.KrakenTimeframe;
import com.common.stategy.pair.BinancePair;
import com.common.stategy.pair.BitstampPair;
import com.common.stategy.pair.KrakenPair;
import com.common.stategy.url.BinanceUrlBuilder;
import com.common.stategy.url.BitstampUrlBuilder;
import com.common.stategy.url.KrakenUrlBuilder;

public class MarketFactory {
    public static Market buildMarket(Exchange exchange, Pair pair, Timeframe timeframe) {
        return switch (exchange) {
            case BINANCE -> new Market(exchange, new BinancePair(pair), new BinanceTimeframe(timeframe), new BinanceUrlBuilder());
            case BITSTAMP -> new Market(exchange, new BitstampPair(pair), new BitstampTimeframe(timeframe), new BitstampUrlBuilder());
            case KRAKEN -> new Market(exchange, new KrakenPair(pair), new KrakenTimeframe(timeframe), new KrakenUrlBuilder());
        };
    }
}
