package com.cml.converter;

import com.cml.enums.Timeframe;
import com.cml.enums.Pair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class KrakenConverterTest {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConvertPair_InvalidPair() {
        KrakenConverter.convertPair(Pair.fromString("invalidPair"));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConvertInterval_InvalidInterval() {
        KrakenConverter.convertTimeframe(Timeframe.fromString("invalidInterval"));
    }

    @Test(dataProvider = "pairData")
    public void testConvertPair(Pair pair, String expected) {
        assertEquals(KrakenConverter.convertPair(pair), expected);
    }

    @Test(dataProvider = "intervalData")
    public void testConvertInterval(Timeframe timeframe, String expected) {
        assertEquals(KrakenConverter.convertTimeframe(timeframe), expected);
    }

    @DataProvider(name = "pairData")
    public Object[][] pairData() {
        return new Object[][] {
                { Pair.EUR_BTC, "XXBTZEUR" },
                { Pair.EUR_ETH, "XETHZEUR" }
        };
    }

    @DataProvider(name = "intervalData")
    public Object[][] intervalData() {
        return new Object[][] {
                { Timeframe.ONE_MINUTE, "1" },
                { Timeframe.FIVE_MINUTES, "5" },
                { Timeframe.FIFTEEN_MINUTES, "15" },
                { Timeframe.THIRTY_MINUTES, "30" },
                { Timeframe.ONE_HOUR, "60" },
                { Timeframe.FOUR_HOURS, "240" },
                { Timeframe.ONE_DAY, "1440" }
        };
    }
}
