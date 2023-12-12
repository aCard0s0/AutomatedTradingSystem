package com.common.stategy.pair;

import com.common.converter.BitstampConverter;
import com.common.enums.Pair;
import com.google.common.base.Objects;

public record BitstampPair(Pair pair) implements PairStrategy {
    @Override
    public Pair getPair() {
        return pair;
    }

    @Override
    public String getExchangePair() {
        return BitstampConverter.convertPair(pair);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BitstampPair that)) return false;
        return pair == that.pair;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pair);
    }
}
