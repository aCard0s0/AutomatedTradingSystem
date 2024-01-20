package com.mws.mapper;

import static com.mws.utils.MarketTableMockGenerator.buildMockMarketTableWith;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class MarketMapperTest {
    /*
    private MarketMapperTOELIMINATE victim;

    @BeforeMethod
    void setUp() {
        victim = new MarketMapperTOELIMINATE();
    }

    @Test(dataProvider = "marketTableMap")
    public void testMapToMarket(MarketTable marketTable, Exchange exchange, Pair pair, Timeframe timeframe) {
        Market result = victim.mapToMarket(marketTable);
        assertNotNull(result);
        assertEquals(result.getExchange(), exchange);
        assertEquals(result.getPair(), pair);
        assertEquals(result.getTimeframe(), timeframe);
    }

    @DataProvider(name = "marketTableMap")
    public Object[][] marketTableMap() {
        return new Object[][] {
                {buildMockMarketTableWith(1L, "binance", "eurbtc", "1m", true),
                        Exchange.BINANCE, Pair.EUR_BTC, Timeframe.ONE_MINUTE},
                {buildMockMarketTableWith(2L, "binance", "eurbtc", "1h", true),
                        Exchange.BINANCE, Pair.EUR_BTC, Timeframe.ONE_HOUR},
                {buildMockMarketTableWith(3L, "kraken", "eurbtc", "1d", false),
                        Exchange.KRAKEN, Pair.EUR_BTC, Timeframe.ONE_DAY},
                {buildMockMarketTableWith(4L, "binance", "eurbtc", "1w", false),
                        Exchange.BINANCE, Pair.EUR_BTC, Timeframe.ONE_WEEK},
                {buildMockMarketTableWith(5L, "kraken", "eureth", "1mo", true),
                        Exchange.KRAKEN, Pair.EUR_ETH, Timeframe.ONE_MONTH},
        };
    }
*/
}
