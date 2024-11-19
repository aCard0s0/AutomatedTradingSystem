package cryptobot.cml.converter;

import cryptobot.cml.enums.Timeframe;
import cryptobot.cml.enums.Pair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BinanceConverterTest {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConvertPair_InvalidPair() {
        BinanceConverter.convertPair(Pair.fromString("invalidPair"));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConvertInterval_InvalidInterval() {
        BinanceConverter.convertTimeframe(Timeframe.fromString("invalidInterval"));
    }

    @Test(dataProvider = "pairData")
    public void testConvertPair(Pair pair, String expected) {
        assertEquals(BinanceConverter.convertPair(pair), expected);
    }

    @Test(dataProvider = "intervalData")
    public void testConvertInterval(Timeframe timeframe, String expected) {
        assertEquals(BinanceConverter.convertTimeframe(timeframe), expected);
    }

    @DataProvider(name = "pairData")
    public Object[][] pairData() {
        return new Object[][] {
                { Pair.EUR_BTC, "BTCEUR" },
                { Pair.EUR_ETH, "ETHEUR" },
                { Pair.EUR_ADA, "ADAEUR" },
                { Pair.EUR_XRP, "XRPEUR" }
        };
    }

    @DataProvider(name = "intervalData")
    public Object[][] intervalData() {
        return new Object[][] {
                { Timeframe.ONE_MINUTE, "1m" },
                { Timeframe.FIVE_MINUTES, "5m" },
                { Timeframe.FIFTEEN_MINUTES, "15m" },
                { Timeframe.THIRTY_MINUTES, "30m" },
                { Timeframe.ONE_HOUR, "1h" },
                { Timeframe.FOUR_HOURS, "4h" },
                { Timeframe.ONE_DAY, "1d" }
        };
    }
}
