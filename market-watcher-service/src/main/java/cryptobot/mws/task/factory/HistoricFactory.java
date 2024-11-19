package cryptobot.mws.task.factory;

import cryptobot.cml.model.Market;
import cryptobot.mws.calendar.CalendarUtils;
import cryptobot.mws.httppool.factory.ExchangeApiMap;
import cryptobot.mws.processor.factory.CandlestickProcessorMap;
import cryptobot.mws.service.db.SqlClientService;
import cryptobot.mws.task.TasksMap;
import cryptobot.mws.task.callback.WatcherTaskCallback;
import cryptobot.mws.task.runnable.HistoricRetrieverTask;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@Log4j2
public class HistoricFactory implements TaskFactory {
    private final ExchangeApiMap exchangeApiMap;
    private final CandlestickProcessorMap processorsMap;
    private final SqlClientService sqlClientService;
    private final ExecutorService executorService;
    private final WatcherTaskCallback watcherTaskCallback;
    private final CalendarUtils calendar;
    private final TasksMap tasks;

    public HistoricFactory(ExchangeApiMap exchangeApiMap, CandlestickProcessorMap processorsMap, SqlClientService sqlClientService, ExecutorService executorService, WatcherTaskCallback watcherTaskCallback, CalendarUtils calendar, TasksMap tasks) {
        super();
        this.exchangeApiMap = exchangeApiMap;
        this.processorsMap = processorsMap;
        this.sqlClientService = sqlClientService;
        this.executorService = executorService;
        this.watcherTaskCallback = watcherTaskCallback;
        this.calendar = calendar;
        this.tasks = tasks;
    }

    public void createTask(Market market) {
        HistoricRetrieverTask task = new HistoricRetrieverTask(
                market,
                exchangeApiMap.getApiClient(market.getExchange()),
                processorsMap.getProcessor(market.getExchange()),
                sqlClientService,
                watcherTaskCallback,
                calendar);

        Future<?> submittedTask = executorService.submit(task);
        tasks.addTask(market.getMarketCode(), submittedTask);

        log.info("operation=createTask, message='HistoricDataRetrieverTask submitted', market.code='{}'", market.getMarketCode());
    }
}
