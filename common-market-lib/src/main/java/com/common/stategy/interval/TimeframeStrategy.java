package com.common.stategy.interval;

import com.common.enums.Timeframe;

public interface TimeframeStrategy {
    /**
     * @return the timeframe of the candlestick.
     * Possible values: 1m, 5m, 15m, 30m, 1h, 4h, 1d, 1w, 1M
     */
    Timeframe getTimeframe();

    /**
     * @return the timeframe of the candlestick in the exchange.
     * For example. this is useful to set parameter when calling the exchange API.
     */
    String getExchangeTimeframe();

    /**
     * @return the cron expression to schedule the candlestick retrieval.
     * For example, the 1h timeframe will have the cron expression "0 0 * * *"
     */
    String getCronExpression();
}
