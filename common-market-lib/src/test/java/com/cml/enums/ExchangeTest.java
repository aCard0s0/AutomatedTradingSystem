package com.cml.enums;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ExchangeTest {
    @Test
    public void fromString_InvalidExchangeHouse_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Exchange.fromString("INVALID_EXCHANGE"));
    }

    @Test(dataProvider = "exchangeHouseProvider")
    public void fromString_ValidExchangeHouse_ReturnsEnum(String exchangeHouse, Exchange expected) {
        assertEquals(Exchange.fromString(exchangeHouse), expected);
    }

    @DataProvider
    public static Object[][] exchangeHouseProvider() {
        return new Object[][]{
                {"BINANCE", Exchange.BINANCE},
                {"binance", Exchange.BINANCE},
                {"BITSTAMP", Exchange.BITSTAMP},
                {"Bitstamp", Exchange.BITSTAMP},
                {"KRAKEN", Exchange.KRAKEN},
                {"kRaKen", Exchange.KRAKEN}
        };
    }
}
