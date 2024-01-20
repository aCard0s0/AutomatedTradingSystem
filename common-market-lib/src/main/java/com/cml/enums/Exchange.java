package com.cml.enums;

import lombok.Getter;

import static com.google.common.base.Preconditions.checkArgument;

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
        checkArgument(exchangeHouse != null && !exchangeHouse.isBlank(), "Invalid Exchange House: %s", exchangeHouse);

        for (Exchange house : Exchange.values()) {
            if (house.exchange.equalsIgnoreCase(exchangeHouse)) {
                return house;
            }
        }
        throw new IllegalArgumentException("Invalid Exchange House: " + exchangeHouse); // TODO: Add custom exception "NotSupportedExchangeException"
    }

    @Override
    public String toString() {
        return "Exchange{'%s'}".formatted(exchange);
    }
}
