package cryptobot.cml.enums;

import lombok.Getter;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
public enum Timeframe {
    ONE_MINUTE("1m"),
    FIVE_MINUTES("5m"),
    FIFTEEN_MINUTES("15m"),
    THIRTY_MINUTES("30m"),
    ONE_HOUR("1h"),
    FOUR_HOURS("4h"),
    ONE_DAY("1d"),
    ONE_WEEK("1w"),
    ONE_MONTH("1mo");

    private final String timeframe;

    Timeframe(String timeframe) {
        this.timeframe = timeframe;
    }

    public static Timeframe fromString(String interval) {
        checkArgument(interval != null && !interval.isBlank(), "Invalid Timeframe: %s", interval);

        for (Timeframe i : Timeframe.values()) {
            if (i.timeframe.equalsIgnoreCase(interval)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid Timeframe: " + interval); // TODO: Add custom exception "NotSupportedIntervalException"
    }

    @Override
    public String toString() {
        return "Timeframe{'%s'}".formatted(timeframe);
    }
}
