package com.mml.strategy.timeframe;

import com.mml.converter.BitstampConverter;
import com.mml.enums.Timeframe;
import com.google.common.base.Objects;

public record BitstampTimeframe(Timeframe timeframe) implements TimeframeStrategy {
    @Override
    public Timeframe getTimeframe() {
        return timeframe;
    }
    @Override
    public String getExchangeTimeframe() {
        return BitstampConverter.convertTimeframe(timeframe);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BitstampTimeframe that)) return false;
        return timeframe == that.timeframe;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(timeframe);
    }

    @Override
    public String toString() {
        return timeframe.getTimeframe();
    }
}