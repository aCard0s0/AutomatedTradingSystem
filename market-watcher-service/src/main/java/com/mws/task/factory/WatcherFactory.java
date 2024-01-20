package com.mws.task.factory;

import com.cml.model.Market;
import com.mws.httppool.factory.ExchangeApiMap;
import com.mws.processor.factory.CandlestickProcessorMap;
import com.mws.service.db.SqlClientService;
import com.mws.task.CronExpression;
import com.mws.task.TasksMap;
import com.mws.task.runnable.MarketWatcherTask;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;

@Service
@Log4j2
public class WatcherFactory implements TaskFactory {
    private final ExchangeApiMap exchangeApiMap;
    private final CandlestickProcessorMap processorsMap;
    private final SqlClientService sqlClientService;
    private final TaskScheduler taskScheduler;
    private final CronExpression cronExpression;
    private final TasksMap tasks;

    public WatcherFactory(ExchangeApiMap exchangeApiMap, CandlestickProcessorMap processorsMap, SqlClientService sqlClientService, TaskScheduler taskScheduler, CronExpression cronExpression, TasksMap tasks, TasksMap tasks1) {
        this.exchangeApiMap = exchangeApiMap;
        this.processorsMap = processorsMap;
        this.sqlClientService = sqlClientService;
        this.taskScheduler = taskScheduler;
        this.cronExpression = cronExpression;
        this.tasks = tasks1;
    }

    public void createTask(Market market) {
        MarketWatcherTask task = new MarketWatcherTask(
                market,
                exchangeApiMap.getApiClient(market.getExchange()),
                processorsMap.getProcessor(market.getExchange()),
                sqlClientService);

        String cron = cronExpression.getCronExpression(market);
        ScheduledFuture<?> scheduleTask = taskScheduler.schedule(task, new CronTrigger(cron));
        tasks.addTask(market.getMarketCode(), scheduleTask);

        log.info("operation=createTask, message='MarketWatcherTask schedule', cron='{}', market.code='{}'", cron, market.getMarketCode());
    }
}
