package cryptobot.mws.task.runnable;

import cryptobot.mws.calendar.CalendarUtils;
 import cryptobot.mws.httppool.ExchangeApi;
import cryptobot.mws.processor.CandlestickProcessor;
import cryptobot.mws.service.db.SqlClientService;
import cryptobot.mws.task.callback.WatcherTaskCallback;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static cryptobot.mws.utils.CandlestickMockGenerator.readKrakenRawCandlestickData;
import static cryptobot.mws.utils.CandlestickMockGenerator.readProcessedCandlestickData;
import static cryptobot.mws.utils.MarketMockGenerator.buildMockMarket;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HistoricRetrieverTaskTest {
    @InjectMocks
    private HistoricRetrieverTask victim;
    @Mock
    private ExchangeApi apiClient;
    @Mock
    private SqlClientService sqlClientService;
    @Mock
    private CandlestickProcessor processorService;
    @Mock
    private WatcherTaskCallback watcherTaskCallback;
    @Mock
    private CalendarUtils calendar;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        victim = new HistoricRetrieverTask(buildMockMarket(), apiClient, processorService, sqlClientService, watcherTaskCallback, calendar);
    }

    @Test
    public void shouldCreateTableAndFetchInitialCandlesticks() {
        doNothing().when(sqlClientService).createTableIfNotExists(any());
        when(sqlClientService.getLastCandlestick(any())).thenReturn(Optional.empty());
        when(apiClient.fetchPublicMarketData(any())).thenReturn(readKrakenRawCandlestickData());
        when(processorService.process(any(), any())).thenReturn(CompletableFuture.completedFuture(readProcessedCandlestickData()));
        doNothing().when(sqlClientService).insertIntoTable(any(), any());
        when(calendar.getCurrentCandleTimestampForTimeframe(any())).thenReturn(LocalDateTime.of(2023, 11, 21, 6, 0));
        doNothing().when(watcherTaskCallback).onTaskCompleted(any());

        victim.run();

        verify(sqlClientService, times(1)).createTableIfNotExists(any());
        verify(sqlClientService, times(1)).getLastCandlestick(any());
        verify(apiClient,times(1)).fetchPublicMarketData(any());
        verify(processorService, times(1)).process(any(), any());
        verify(sqlClientService, times(1)).insertIntoTable(any(), any());
        verify(watcherTaskCallback, times(1)).onTaskCompleted(any());
        verify(calendar, times(2)).getCurrentCandleTimestampForTimeframe(any());
    }

    @Test
    public void shouldNotCreateTableAndFetchCandlesticks() {

    }

    @Test
    public void shouldNotCreateTableAndNotFetchCandlesticks() {

    }
}
