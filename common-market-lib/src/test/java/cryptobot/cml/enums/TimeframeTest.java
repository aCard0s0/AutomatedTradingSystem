package cryptobot.cml.enums;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class TimeframeTest {
    @Test
    public void fromString_InvalidInterval_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Timeframe.fromString("INVALID_INTERVAL"));
    }

    @Test(dataProvider = "intervalProvider")
    public void fromString_ValidExchangeHouse_ReturnsEnum(String interval, Timeframe expected) {
        assertEquals(Timeframe.fromString(interval), expected);
    }

    @DataProvider
    public static Object[][] intervalProvider() {
        return new Object[][]{
                {"1m", Timeframe.ONE_MINUTE},
                {"5m", Timeframe.FIVE_MINUTES},
                {"15m", Timeframe.FIFTEEN_MINUTES},
                {"30m", Timeframe.THIRTY_MINUTES},
                {"1h", Timeframe.ONE_HOUR},
                {"4h", Timeframe.FOUR_HOURS},
                {"1d", Timeframe.ONE_DAY},
                {"1mo", Timeframe.ONE_MONTH}
        };
    }
}
