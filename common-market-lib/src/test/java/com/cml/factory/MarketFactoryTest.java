package com.cml.factory;

import com.cml.enums.Exchange;
import com.cml.enums.Timeframe;
import com.cml.enums.Pair;
import com.cml.model.Market;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class MarketFactoryTest {

    @Test
    public void testBuildMarket() {
        Exchange exchange1 = Exchange.BINANCE;
        Exchange exchange2 = Exchange.BITSTAMP;
        Exchange exchange3 = Exchange.KRAKEN;
        Pair pair = Pair.EUR_BTC;
        Timeframe timeframe = Timeframe.ONE_HOUR;

        Market market1 = MarketFactory.buildMarket(exchange1, pair, timeframe);
        Market market2 = MarketFactory.buildMarket(exchange2, pair, timeframe);
        Market market3 = MarketFactory.buildMarket(exchange3, pair, timeframe);

        assertNotNull(market1);
        assertNotNull(market2);
        assertNotNull(market3);

        assertEquals(market1.getExchange(), exchange1);
        assertEquals(market2.getExchange(), exchange2);
        assertEquals(market3.getExchange(), exchange3);
    }
}

