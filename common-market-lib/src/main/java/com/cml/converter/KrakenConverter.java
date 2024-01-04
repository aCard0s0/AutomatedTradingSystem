package com.cml.converter;

import com.cml.enums.Timeframe;
import com.cml.enums.Pair;

public class KrakenConverter {
    private KrakenConverter() {
    }

    public static String convertPair(Pair pair) {
        return switch (pair) {
            case EUR_BTC -> "XXBTZEUR";
            case EUR_ETH -> "XETHZEUR";
            default -> throw new IllegalArgumentException("Invalid Kraken Pair: " + pair);
        };
    }

    public static String convertTimeframe(Timeframe timeframe) {
        return switch (timeframe) {
            case ONE_MINUTE -> "1";
            case FIVE_MINUTES -> "5";
            case FIFTEEN_MINUTES -> "15";
            case THIRTY_MINUTES -> "30";
            case ONE_HOUR -> "60";
            case FOUR_HOURS -> "240";
            case ONE_DAY -> "1440";
            case ONE_WEEK -> "10080";
            // FIFTEEN_DAYS -> "21600";
            default -> throw new IllegalArgumentException("Invalid Kraken Timeframe: " + timeframe);
        };
    }

}
