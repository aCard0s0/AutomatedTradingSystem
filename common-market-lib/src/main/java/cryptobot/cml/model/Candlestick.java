package cryptobot.cml.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Getter
@Setter
public class Candlestick {
    private String pair;
    private String timeframe;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal volume;
    private Instant openTime;
    private Instant closeTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candlestick that)) return false;
        return Objects.equal(pair, that.pair) &&
                Objects.equal(timeframe, that.timeframe) &&
                Objects.equal(open, that.open) &&
                Objects.equal(high, that.high) &&
                Objects.equal(low, that.low) &&
                Objects.equal(close, that.close) &&
                Objects.equal(volume, that.volume) &&
                Objects.equal(openTime, that.openTime) &&
                Objects.equal(closeTime, that.closeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pair, timeframe, open, high, low, close, volume, openTime, closeTime);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("pair", pair)
                .add("timeframe", timeframe)
                .add("open", open)
                .add("high", high)
                .add("low", low)
                .add("close", close)
                .add("volume", volume)
                .add("openTime", openTime)
                .add("closeTime", closeTime)
                .toString();
    }
}
