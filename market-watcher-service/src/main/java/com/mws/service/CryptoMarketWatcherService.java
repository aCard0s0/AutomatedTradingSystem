package com.mws.service;

import com.cml.model.Market;
import com.mws.service.api.MarketService;
import com.mws.task.factory.TaskFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CryptoMarketWatcherService implements MarketWatcherService {
    private final MarketService marketService;
    private final TaskFactory taskFactory;

    public CryptoMarketWatcherService(MarketService marketService,
                                      @Qualifier("historicFactory") TaskFactory taskFactory) {
        this.marketService = marketService;
        this.taskFactory = taskFactory;
    }

    @Override
    public void startMarketTasks() {
        List<Market> markets = marketService.getAllActiveMarkets();
        markets.forEach(taskFactory::createTask);
    }
}
