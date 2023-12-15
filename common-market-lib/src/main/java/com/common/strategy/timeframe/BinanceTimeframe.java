package com.common.strategy.timeframe;

import com.common.converter.BinanceConverter;
import com.common.enums.Timeframe;
import com.google.common.base.Objects;

public record BinanceTimeframe(Timeframe timeframe) implements TimeframeStrategy {
    @Override
    public Timeframe getTimeframe() {
        return timeframe;
    }
    @Override
    public String getExchangeTimeframe() {
        return BinanceConverter.convertTimeframe(timeframe);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BinanceTimeframe that)) return false;
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
