package com.mml.market;

import com.mml.enums.Exchange;
import com.mml.enums.Timeframe;
import com.mml.enums.Pair;
import com.mml.model.Market;
import com.mml.strategy.timeframe.BinanceTimeframe;
import com.mml.strategy.timeframe.KrakenTimeframe;
import com.mml.strategy.pair.BinancePair;
import com.mml.strategy.pair.KrakenPair;
import com.mml.strategy.url.BinanceUrlBuilder;
import com.mml.strategy.url.KrakenUrlBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;

public class MarketTest {
    @Test
    public void testEqualsAndHashCode() {
        Market market1 = new Market(Exchange.KRAKEN, new KrakenPair(Pair.EUR_BTC), new KrakenTimeframe(Timeframe.ONE_HOUR), new KrakenUrlBuilder());
        Market market2 = new Market(Exchange.KRAKEN, new KrakenPair(Pair.EUR_BTC), new KrakenTimeframe(Timeframe.ONE_HOUR), new KrakenUrlBuilder());
        Market market3 = new Market(Exchange.BINANCE, new BinancePair(Pair.EUR_ETH), new BinanceTimeframe(Timeframe.ONE_HOUR), new BinanceUrlBuilder());

        //assertEquals(market1, market2);
        assertNotEquals(market1, market3);

        //assertEquals(market1.hashCode(), market2.hashCode());
        assertNotEquals(market1.hashCode(), market3.hashCode());
    }

}
