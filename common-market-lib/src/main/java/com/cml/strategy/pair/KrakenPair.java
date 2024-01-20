package com.cml.strategy.pair;

import com.cml.converter.KrakenConverter;
import com.cml.enums.Pair;
import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.checkArgument;

public record KrakenPair(Pair pair) implements PairStrategy {
    public KrakenPair {
        checkArgument(pair != null, "Pair cannot be null");
    }

    @Override
    public Pair getPair() {
        return pair;
    }

    @Override
    public String getExchangePair() {
        return KrakenConverter.convertPair(pair);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KrakenPair that)) return false;
        return pair == that.pair;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pair);
    }

    @Override
    public String toString() {
        return pair.getPair();
    }
}
