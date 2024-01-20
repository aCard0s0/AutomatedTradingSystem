package com.mws.task;

import com.cml.model.Market;
import org.springframework.stereotype.Component;

/**
 * A Spring Scheduled tasks:
 * 1 2 3 4 5 6 Index
 * - - - - - -
 * * * * * * * command to be executed
 * - - - - - -
 * | | | | | |
 * | | | | | ------- Day of week (MON - SUN)
 * | | | | --------- Month (1 - 12)
 * | | | ----------- Day of month (1 - 31)
 * | |-------------- Hour (0 - 23)
 * | --------------- Minute (0 - 59)
 * ----------------- Seconds (0 - 59)
 *
 */
@Component
public class CronExpression {
    public String getCronExpression(Market market) {
        return switch (market.getTimeframe()) {
            case ONE_MINUTE -> "1 * * * * *";   // "10 * * * * ?" 10th second of every minute.
            case FIVE_MINUTES -> "*/5 * * * *";
            case FIFTEEN_MINUTES -> "*/15 * * * *";
            case THIRTY_MINUTES -> "0,30 * * * *";
            case ONE_HOUR -> "0 0 */1 * * *";
            case FOUR_HOURS -> "0 */4 * * *";
            case ONE_DAY -> "0 0 * * * *";
            case ONE_WEEK -> "0 0 * * 0";
            case ONE_MONTH -> "0 0 1 * *";
            default -> throw new IllegalArgumentException("Invalid Kraken Timeframe: " + market.getTimeframeStr());
        };
    }
}
