package com.common.market;

import com.common.enums.Exchange;
import com.common.enums.Timeframe;
import com.common.enums.Pair;
import com.common.model.Market;
import com.common.strategy.timeframe.BinanceTimeframe;
import com.common.strategy.timeframe.KrakenTimeframe;
import com.common.strategy.pair.BinancePair;
import com.common.strategy.pair.KrakenPair;
import com.common.strategy.url.BinanceUrlBuilder;
import com.common.strategy.url.KrakenUrlBuilder;
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
