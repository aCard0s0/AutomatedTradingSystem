package com.common.enums;

public enum Exchange {
    BINANCE,
    BITSTAMP,
    KRAKEN;

    public static Exchange fromString(String exchangeHouse) {
        System.out.println(exchangeHouse);
        for (Exchange house : Exchange.values()) {
            if (String.valueOf(house).equalsIgnoreCase(exchangeHouse)) {
                return house;
            }
        }
        throw new IllegalArgumentException("Invalid Exchange House: " + exchangeHouse);
    }
}
