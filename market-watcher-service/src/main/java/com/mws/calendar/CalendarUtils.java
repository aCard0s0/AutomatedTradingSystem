package com.mws.calendar;

import com.cml.model.Market;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("singleton")
public class CalendarUtils {
    public LocalDateTime getCurrentCandleTimestampForTimeframe(Market market) {
        LocalDateTime baseTime = LocalDateTime.now().withSecond(0).withNano(0);
        return switch (market.getTimeframe()) {
            case ONE_MINUTE -> baseTime;
            case FIVE_MINUTES -> baseTime.minusMinutes(LocalDateTime.now().getMinute() % 5);
            case FIFTEEN_MINUTES -> baseTime.minusMinutes(LocalDateTime.now().getMinute() % 15);
            case THIRTY_MINUTES -> baseTime.minusMinutes(LocalDateTime.now().getMinute() % 30);
            case ONE_HOUR -> baseTime.minusMinutes(LocalDateTime.now().getMinute());
            case FOUR_HOURS -> baseTime.minusHours(LocalDateTime.now().getHour() % 4);
            case ONE_DAY -> baseTime.withMinute(0).minusHours(LocalDateTime.now().getHour());
            case ONE_WEEK -> baseTime.withMinute(0).minusDays(LocalDateTime.now().getDayOfWeek().getValue() % 7);
            case ONE_MONTH -> baseTime.minusDays(LocalDateTime.now().getDayOfMonth() - 1);
            default -> throw new IllegalArgumentException("Invalid Timeframe: " + market.getTimeframe());
        };
    }
}
