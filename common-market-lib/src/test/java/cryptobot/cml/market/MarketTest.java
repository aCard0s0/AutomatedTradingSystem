package cryptobot.cml.market;

import cryptobot.cml.enums.Exchange;
import cryptobot.cml.enums.Timeframe;
import cryptobot.cml.enums.Pair;
import cryptobot.cml.model.Market;
import cryptobot.cml.strategy.timeframe.BinanceTimeframe;
import cryptobot.cml.strategy.timeframe.KrakenTimeframe;
import cryptobot.cml.strategy.pair.BinancePair;
import cryptobot.cml.strategy.pair.KrakenPair;
import cryptobot.cml.strategy.url.BinanceUrlBuilder;
import cryptobot.cml.strategy.url.KrakenUrlBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class MarketTest {
    @Test
    public void testEqualsAndHashCode() {
        Market market1 = new Market(Exchange.KRAKEN, new KrakenPair(Pair.EUR_BTC), new KrakenTimeframe(Timeframe.ONE_HOUR), new KrakenUrlBuilder());
        Market market2 = new Market(Exchange.KRAKEN, new KrakenPair(Pair.EUR_BTC), new KrakenTimeframe(Timeframe.ONE_HOUR), new KrakenUrlBuilder());
        Market market3 = new Market(Exchange.BINANCE, new BinancePair(Pair.EUR_ETH), new BinanceTimeframe(Timeframe.ONE_HOUR), new BinanceUrlBuilder());

        assertEquals(market1, market2);
        assertNotEquals(market1, market3);

        assertEquals(market1.hashCode(), market2.hashCode());
        assertNotEquals(market1.hashCode(), market3.hashCode());
    }
}
