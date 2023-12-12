package com.common.market;

import com.common.enums.Exchange;
import com.common.enums.Timeframe;
import com.common.enums.Pair;
import com.common.model.Market;
import com.common.stategy.interval.BinanceTimeframe;
import com.common.stategy.interval.KrakenTimeframe;
import com.common.stategy.pair.BinancePair;
import com.common.stategy.pair.KrakenPair;
import com.common.stategy.url.BinanceUrlBuilder;
import com.common.stategy.url.KrakenUrlBuilder;
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
