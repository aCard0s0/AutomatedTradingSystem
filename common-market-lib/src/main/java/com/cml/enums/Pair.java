package com.cml.enums;

import lombok.Getter;

@Getter
public enum Pair {
    EUR_BTC("eurbtc"),
    EUR_ETH("eureth"),
    EUR_XRP("eurxrp"),
    EUR_ADA("eurada");

    private final String pair;

    Pair(String pair) {
        this.pair = pair;
    }

    public static Pair fromString(String pair) {
        for (Pair p : Pair.values()) {
            if (p.pair.equalsIgnoreCase(pair)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid Pair: " + pair);
    }

    @Override
    public String toString() {
        return "Pair{'%s'}".formatted(pair);
    }
}
