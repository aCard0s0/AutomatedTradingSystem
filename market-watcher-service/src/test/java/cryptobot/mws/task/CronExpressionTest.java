package cryptobot.mws.task;

import cryptobot.cml.enums.Timeframe;
import cryptobot.cml.model.Market;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static cryptobot.mws.utils.MarketMockGenerator.buildMockMarketWith;
import static org.testng.Assert.assertEquals;

public class CronExpressionTest {
    @InjectMocks
    private CronExpression victim;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(dataProvider = "marketAndCronExpression")
    public void testGetCronExpression(Market market, String expectedCronExpression) {
        String cronExpression = victim.getCronExpression(market);
        assertEquals(cronExpression, expectedCronExpression);
    }

    @DataProvider(name = "marketAndCronExpression")
    public Object[][] marketAndCronExpression() {
        return new Object[][] {
                {buildMockMarketWith(Timeframe.ONE_MINUTE), "1 * * * * *"},
                {buildMockMarketWith(Timeframe.FIVE_MINUTES), "*/5 * * * *"},
                {buildMockMarketWith(Timeframe.FIFTEEN_MINUTES), "*/15 * * * *"},
                {buildMockMarketWith(Timeframe.THIRTY_MINUTES), "0,30 * * * *"},
                {buildMockMarketWith(Timeframe.ONE_HOUR), "0 0 */1 * * *"},
                {buildMockMarketWith(Timeframe.FOUR_HOURS), "0 */4 * * *"},
                {buildMockMarketWith(Timeframe.ONE_DAY), "0 0 * * * *"},
                {buildMockMarketWith(Timeframe.ONE_WEEK), "0 0 * * 0"},
                {buildMockMarketWith(Timeframe.ONE_MONTH), "0 0 1 * *"}
        };
    }
}
