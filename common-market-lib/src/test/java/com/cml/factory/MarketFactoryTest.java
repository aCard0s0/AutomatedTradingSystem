package com.cml.factory;

import com.cml.enums.Exchange;
import com.cml.enums.Timeframe;
import com.cml.enums.Pair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class MarketFactoryTest {

    @Test(dataProvider = "validEnumArguments")
    public void testBuildMarketWithEnums_Successfully(Exchange exchange, Pair pair, Timeframe timeframe) {
        assertNotNull(MarketFactory.buildMarket(exchange, pair, timeframe));
    }

    @Test(dataProvider = "validStringArguments")
    public void testBuildMarketWithStrings_Successfully(String exchange, String pair, String timeframe) {
        assertNotNull(MarketFactory.buildMarket(exchange, pair, timeframe));
    }

    @Test(dataProvider = "invalidEnumArguments", expectedExceptions = IllegalArgumentException.class)
    public void testBuildMarketWithEnums_ShouldThrowException(Exchange exchange, Pair pair, Timeframe timeframe) {
        MarketFactory.buildMarket(exchange, pair, timeframe);
    }

    @Test(dataProvider = "invalidStringArguments", expectedExceptions = IllegalArgumentException.class)
    public void testBuildMarketWithString_ShouldThrowException(String exchange, String pair, String timeframe) {
        MarketFactory.buildMarket(exchange, pair, timeframe);
    }

    @DataProvider(name = "validEnumArguments")
    private Object[][] validEnumArguments() {
        return new Object[][]{
                {Exchange.BINANCE, Pair.EUR_BTC, Timeframe.ONE_HOUR},
                {Exchange.KRAKEN, Pair.EUR_ADA, Timeframe.ONE_DAY},
                {Exchange.BITSTAMP, Pair.EUR_ETH, Timeframe.FIVE_MINUTES},
        };
    }

    @DataProvider(name = "validStringArguments")
    private Object[][] validStringArguments() {
        return new Object[][]{
                {"binance", "eurbtc", "1m"},
                {"KRAKEN", "eurADA", "1mo"},
                {"bItsTamp", "EUReth", "1D"},
        };
    }

    @DataProvider(name = "invalidEnumArguments")
    private Object[][] invalidEnumArguments() {
        return new Object[][]{
                {null, null, null},
                {null, Pair.EUR_BTC, Timeframe.ONE_HOUR},
                {Exchange.BINANCE, null, Timeframe.ONE_HOUR},
                {Exchange.BINANCE, Pair.EUR_BTC, null},
        };
    }

    @DataProvider(name = "invalidStringArguments")
    private Object[][] invalidStringArguments() {
        return new Object[][]{
                {null, null, null},
                {null, "eurbtc", "1m"},
                {"binance", null, "1m"},
                {"binance", "eurbtc", null},
                {"", "", ""},
                {"invalidExchange", "invalidPair", "invalidInterval"}
        };
    }
}

