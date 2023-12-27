package com.mml.converter;

import com.mml.enums.Timeframe;
import com.mml.enums.Pair;
import lombok.Getter;

public class BitstampConverter {
    @Getter
    private static final BitstampConverter instance;

    static {
        instance = new BitstampConverter();
    }

    public BitstampConverter() {
    }

    public static String convertPair(Pair pair) {
        return switch (pair) {
            case EUR_BTC -> "btceur";
            case EUR_ETH -> "etheur";
            default -> throw new IllegalArgumentException("Invalid Bitstamp Pair: " + pair);
        };
    }

    public static String convertTimeframe(Timeframe timeframe) {
        return switch (timeframe) {
            case ONE_MINUTE -> "60";
            case FIVE_MINUTES -> "300";
            case FIFTEEN_MINUTES -> "900";
            case THIRTY_MINUTES -> "1800";
            case ONE_HOUR -> "3600";
            case FOUR_HOURS -> "14400";
            case ONE_DAY -> "86400";
            default -> throw new IllegalArgumentException("Invalid Bitstamp Interval: " + timeframe);
        };
    }
}
