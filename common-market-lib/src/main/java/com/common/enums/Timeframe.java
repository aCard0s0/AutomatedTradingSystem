package com.common.enums;

public enum Timeframe {
    ONE_MINUTE("1m"),
    FIVE_MINUTES("5m"),
    FIFTEEN_MINUTES("15m"),
    THIRTY_MINUTES("30m"),
    ONE_HOUR("1h"),
    FOUR_HOURS("4h"),
    ONE_DAY("1d"),
    ONE_WEEK("1w"),
    ONE_MONTH("1M");

    private final String interval;

    Timeframe(String interval) {
        this.interval = interval;
    }

    public static Timeframe fromString(String interval) {
        for (Timeframe i : Timeframe.values()) {
            if (i.interval.equals(interval)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid Timeframe: " + interval);
    }
}
