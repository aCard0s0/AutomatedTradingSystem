package com.common.strategy.pair;

import com.common.converter.KrakenConverter;
import com.common.enums.Pair;
import com.google.common.base.Objects;

public record KrakenPair(Pair pair) implements PairStrategy {
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
