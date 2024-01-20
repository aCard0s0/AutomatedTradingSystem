package com.cml.strategy.timeframe;

import com.cml.converter.KrakenConverter;
import com.cml.enums.Timeframe;
import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.checkArgument;

public record KrakenTimeframe(Timeframe timeframe) implements TimeframeStrategy {
    public KrakenTimeframe {
        checkArgument(timeframe != null, "Timeframe cannot be null");
    }

    @Override
    public Timeframe getTimeframe() {
        return timeframe;
    }

    @Override
    public String getExchangeTimeframe() {
        return KrakenConverter.convertTimeframe(timeframe);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KrakenTimeframe that)) return false;
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
