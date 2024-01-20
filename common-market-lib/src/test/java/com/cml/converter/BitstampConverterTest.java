package com.cml.converter;

import com.cml.enums.Timeframe;
import com.cml.enums.Pair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BitstampConverterTest {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConvertPair_InvalidPair() {
        BitstampConverter.convertPair(Pair.fromString("invalidPair"));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConvertInterval_InvalidInterval() {
        BitstampConverter.convertTimeframe(Timeframe.fromString("invalidInterval"));
    }

    @Test(dataProvider = "pairData")
    public void testConvertPair(Pair pair, String expected) {
        assertEquals(BitstampConverter.convertPair(pair), expected);
    }

    @Test(dataProvider = "intervalData")
    public void testConvertInterval(Timeframe timeframe, String expected) {
        assertEquals(BitstampConverter.convertTimeframe(timeframe), expected);
    }

    @DataProvider(name = "pairData")
    public Object[][] pairData() {
        return new Object[][] {
                { Pair.EUR_BTC, "btceur" },
                { Pair.EUR_ETH, "etheur" }
        };
    }

    @DataProvider(name = "intervalData")
    public Object[][] intervalData() {
        return new Object[][] {
                { Timeframe.ONE_MINUTE, "60" },
                { Timeframe.FIVE_MINUTES, "300" },
                { Timeframe.FIFTEEN_MINUTES, "900" },
                { Timeframe.THIRTY_MINUTES, "1800" },
                { Timeframe.ONE_HOUR, "3600" },
                { Timeframe.FOUR_HOURS, "14400" },
                { Timeframe.ONE_DAY, "86400" }
        };
    }
}
