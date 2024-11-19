package cryptobot.cml.enums;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class PairTest {
    @Test
    public void fromString_InvalidInterval_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Pair.fromString("INVALID_PAIR"));
    }

    @Test(dataProvider = "pairProvider")
    public void fromString_ValidExchangeHouse_ReturnsEnum(String interval, Pair expected) {
        assertEquals(Pair.fromString(interval), expected);
    }

    @DataProvider
    public static Object[][] pairProvider() {
        return new Object[][]{
                {"eurbtc", Pair.EUR_BTC},
                {"eureth", Pair.EUR_ETH},
                {"eurxrp", Pair.EUR_XRP},
                {"eurada", Pair.EUR_ADA}
        };
    }
}
