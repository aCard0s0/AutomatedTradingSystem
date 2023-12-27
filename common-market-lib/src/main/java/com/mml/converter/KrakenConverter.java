package com.mml.converter;

import com.mml.enums.Timeframe;
import com.mml.enums.Pair;
import lombok.Getter;

public class KrakenConverter {
    @Getter
    private static final KrakenConverter instance;

    static {
        instance = new KrakenConverter();
    }

    public KrakenConverter() {
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
            default -> throw new IllegalArgumentException("Invalid Kraken Timeframe: " + timeframe);
        };
    }

}
