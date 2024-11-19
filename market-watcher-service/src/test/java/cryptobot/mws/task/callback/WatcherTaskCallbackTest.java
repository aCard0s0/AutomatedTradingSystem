package cryptobot.mws.task.callback;

import cryptobot.mws.task.factory.TaskFactory;
import cryptobot.mws.task.CronExpression;
import cryptobot.mws.task.runnable.MarketWatcherTask;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static cryptobot.mws.utils.MarketMockGenerator.buildMockMarket;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Ignore
public class WatcherTaskCallbackTest {
    @InjectMocks
    private WatcherTaskCallback watcherTaskCallback;
    @Mock
    private TaskScheduler taskScheduler;
    @Mock
    private CronExpression cronExpression;

    @Mock
    private TaskFactory taskFactory;

    public WatcherTaskCallbackTest() {
        MockitoAnnotations.openMocks(this);
        watcherTaskCallback = new WatcherTaskCallback(taskFactory);
    }

    @Test
    void onTaskCompletedShouldScheduleMarketWatcherTask() {
        when(cronExpression.getCronExpression(any())).thenReturn("0 0 12 * * ?");

        watcherTaskCallback.onTaskCompleted(buildMockMarket());

        verify(taskScheduler, times(1)).schedule(
                any(MarketWatcherTask.class),
                any(CronTrigger.class)
        );
    }
}
