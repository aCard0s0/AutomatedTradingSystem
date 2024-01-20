package com.mws.service;

import com.cml.model.Market;
import com.mws.service.api.MarketService;
import com.mws.task.runnable.HistoricRetrieverTask;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;

import static com.mws.utils.MarketMockGenerator.buildMockMarket;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Ignore
public class CryptoMarketWatcherServiceTest {
    @InjectMocks
    private CryptoMarketWatcherService victim;
    @Mock
    private ExecutorService executorService;
    @Mock
    private MarketService marketService;

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStartMarketTasks() {
        List<Market> mockMarkets = List.of(
                buildMockMarket(),
                buildMockMarket(),
                buildMockMarket()
        );
        when(marketService.getAllActiveMarkets()).thenReturn(mockMarkets);

        victim.startMarketTasks();

        verify(executorService, times(mockMarkets.size())).submit(any(HistoricRetrieverTask.class));
    }
}
