package cryptobot.mws.task.runnable;

import cryptobot.mws.httppool.ExchangeApi;
import cryptobot.mws.processor.CandlestickProcessor;
import cryptobot.mws.service.db.SqlClientService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static cryptobot.mws.utils.CandlestickMockGenerator.buildMockCandlestick;
import static cryptobot.mws.utils.CandlestickMockGenerator.readKrakenRawCandlestickData;
import static cryptobot.mws.utils.CandlestickMockGenerator.readProcessedCandlestickData;
import static cryptobot.mws.utils.MarketMockGenerator.buildMockMarket;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MarketWatcherTaskTest {
    @InjectMocks
    private MarketWatcherTask victim;
    @Mock
    private ExchangeApi apiClient;
    @Mock
    private SqlClientService sqlClientService;
    @Mock
    private CandlestickProcessor processorService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        victim = new MarketWatcherTask(buildMockMarket(), apiClient, processorService, sqlClientService);
    }

    @Test
    public void shouldNotPerformAnyActionWhenLastCandlestickIsNotFound() {
        when(sqlClientService.getLastCandlestick(any())).thenReturn(Optional.empty());

        victim.run();

        verify(sqlClientService, times(1)).getLastCandlestick(any());
        verify(apiClient, times(0)).fetchPublicMarketData(any());
        verify(processorService, times(0)).process(any(), any());
        verify(sqlClientService, times(0)).insertIntoTable(any(), any());
    }

    @Test
    void shouldPerformActionsWhenLastCandlestickIsFound() {
        when(sqlClientService.getLastCandlestick(any())).thenReturn(Optional.ofNullable(buildMockCandlestick()));
        when(apiClient.fetchPublicMarketData(anyString())).thenReturn(readKrakenRawCandlestickData());
        when(processorService.process(any(), anyString())).thenReturn(CompletableFuture.completedFuture(readProcessedCandlestickData()));
        doNothing().when(sqlClientService).insertIntoTable(any(), any());

        victim.run();

        verify(sqlClientService, times(1)).getLastCandlestick(any());
        verify(apiClient, times(1)).fetchPublicMarketData(any());
        verify(processorService, times(1)).process(any(), any());
        verify(sqlClientService, times(1)).insertIntoTable(any(), any());
    }
}
