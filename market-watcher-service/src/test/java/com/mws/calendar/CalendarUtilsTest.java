package com.mws.calendar;

import com.cml.enums.Timeframe;
import com.cml.model.Market;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static com.mws.utils.MarketMockGenerator.buildMockMarketWith;
import static org.testng.Assert.assertEquals;

@Test
public class CalendarUtilsTest {
    @InjectMocks
    private CalendarUtils victim;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(dataProvider = "marketAndTimeframe")
    public void testGetCurrentCandleTimestampForTimeframe(Market market, LocalDateTime expectedTimestamp) {
        LocalDateTime actualTimestamp = victim.getCurrentCandleTimestampForTimeframe(market);
        assertEquals(actualTimestamp, expectedTimestamp);
    }

    @DataProvider(name = "marketAndTimeframe")
    public Object[][] marketAndTimeframe() {
        LocalDateTime baseTime = LocalDateTime.now().withSecond(0).withNano(0);
        return new Object[][] {
                {buildMockMarketWith(Timeframe.ONE_MINUTE), baseTime},
                {buildMockMarketWith(Timeframe.FIVE_MINUTES), baseTime.minusMinutes(LocalDateTime.now().getMinute() % 5)},
                {buildMockMarketWith(Timeframe.FIFTEEN_MINUTES), baseTime.minusMinutes(LocalDateTime.now().getMinute() % 15)},
                {buildMockMarketWith(Timeframe.THIRTY_MINUTES), baseTime.minusMinutes(LocalDateTime.now().getMinute() % 30)},
                {buildMockMarketWith(Timeframe.ONE_HOUR), baseTime.minusMinutes(LocalDateTime.now().getMinute())},
                {buildMockMarketWith(Timeframe.FOUR_HOURS), baseTime.minusHours(LocalDateTime.now().getHour() % 4)},
                {buildMockMarketWith(Timeframe.ONE_DAY), baseTime.withMinute(0).minusHours(LocalDateTime.now().getHour())},
                {buildMockMarketWith(Timeframe.ONE_WEEK), baseTime.withMinute(0).minusDays(LocalDateTime.now().getDayOfWeek().getValue() % 7)},
                {buildMockMarketWith(Timeframe.ONE_MONTH), baseTime.minusDays(LocalDateTime.now().getDayOfMonth() - 1)} // TODO: It this correct?
        };
    }

}
