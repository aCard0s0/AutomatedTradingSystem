package cryptobot.mws.utils;

import cryptobot.cml.enums.Exchange;
import cryptobot.cml.enums.Pair;
import cryptobot.cml.enums.Timeframe;
import cryptobot.cml.factory.MarketFactory;
import cryptobot.cml.model.Market;

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
