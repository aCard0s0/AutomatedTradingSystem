package com.common.stategy.interval;

import com.common.converter.CronExpressionConverter;
import com.common.converter.KrakenConverter;
import com.common.enums.Timeframe;
import com.google.common.base.Objects;

public record KrakenTimeframe(Timeframe timeframe) implements TimeframeStrategy {
    @Override
    public Timeframe getTimeframe() {
        return timeframe;
    }

    @Override
    public String getExchangeTimeframe() {
        return KrakenConverter.convertTimeframe(timeframe);
    }

    @Override
    public String getCronExpression() {
        return CronExpressionConverter.convertToCronExpression(timeframe);
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
}
