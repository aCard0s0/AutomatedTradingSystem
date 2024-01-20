package com.cml.enums;

import lombok.Getter;

import static com.google.common.base.Preconditions.checkArgument;

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
        checkArgument(pair != null && !pair.isBlank(), "Invalid Pair: %s", pair);

        for (Pair p : Pair.values()) {
            if (p.pair.equalsIgnoreCase(pair)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid Pair: " + pair); // TODO: Add custom exception "NotSupportedPairException"
    }

    @Override
    public String toString() {
        return "Pair{'%s'}".formatted(pair);
    }
}
