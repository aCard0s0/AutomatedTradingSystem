package com.common.enums;

import lombok.Getter;

@Getter
public enum Exchange {
    BINANCE("binance"),
    BITSTAMP("bitstamp"),
    KRAKEN("kraken");

    private final String exchange;

    Exchange(String exchange) {
        this.exchange = exchange;
    }

    public static Exchange fromString(String exchangeHouse) {
        for (Exchange house : Exchange.values()) {
            if (house.exchange.equalsIgnoreCase(exchangeHouse)) {
                return house;
            }
        }
        throw new IllegalArgumentException("Invalid Exchange House: " + exchangeHouse);
    }

    @Override
    public String toString() {
        return "Exchange{'%s'}".formatted(exchange);
    }
}
