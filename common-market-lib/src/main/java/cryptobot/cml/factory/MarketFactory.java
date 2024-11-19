package cryptobot.cml.factory;

import cryptobot.cml.model.Market;
import cryptobot.cml.enums.Exchange;
import cryptobot.cml.enums.Timeframe;
import cryptobot.cml.enums.Pair;
import cryptobot.cml.strategy.timeframe.BinanceTimeframe;
import cryptobot.cml.strategy.timeframe.BitstampTimeframe;
import cryptobot.cml.strategy.timeframe.KrakenTimeframe;
import cryptobot.cml.strategy.pair.BinancePair;
import cryptobot.cml.strategy.pair.BitstampPair;
import cryptobot.cml.strategy.pair.KrakenPair;
import cryptobot.cml.strategy.url.BinanceUrlBuilder;
import cryptobot.cml.strategy.url.BitstampUrlBuilder;
import cryptobot.cml.strategy.url.KrakenUrlBuilder;

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
