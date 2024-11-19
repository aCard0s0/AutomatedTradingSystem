package cryptobot.cml.strategy.timeframe;

import cryptobot.cml.converter.BinanceConverter;
import cryptobot.cml.enums.Timeframe;
import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.checkArgument;

public record BinanceTimeframe(Timeframe timeframe) implements TimeframeStrategy {
    public BinanceTimeframe {
        checkArgument(timeframe != null, "Timeframe cannot be null");
    }

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
