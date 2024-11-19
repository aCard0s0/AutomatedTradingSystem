package cryptobot.mws.domain.request;

import static com.google.common.base.Preconditions.checkArgument;

public record TaskInput(String marketCode) {
    public TaskInput {
        checkArgument(marketCode != null && !marketCode.isEmpty() && marketCode.split("_").length == 3,
                "Market code cannot be null or empty and must have the format 'exchange_pair_timeframe'");
    }
}
