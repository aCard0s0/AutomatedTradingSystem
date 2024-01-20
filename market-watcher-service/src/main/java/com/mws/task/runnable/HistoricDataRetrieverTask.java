package com.mws.task.runnable;

import com.cml.model.Market;
import com.mws.calendar.CalendarUtils;
import com.mws.domain.CandlestickEntity;
import com.mws.httppool.ExchangeApi;
import com.mws.processor.CandlestickProcessor;
import com.mws.service.db.SqlClientService;
import com.mws.task.callback.WatcherTaskCallback;
import lombok.extern.log4j.Log4j2;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

/**
 *  This class is responsible for:
 *      1. creating the table if it does not exist,
 *      2. fetching the last candlestick from the database,
 *      3. fetching the raw market data from the exchange API,
 *      4. processing the raw market data,
 *      5. inserting the candlesticks into the database.
 *      6. calling the callback when the task is completed.
 *
 */
@Log4j2
public class HistoricDataRetrieverTask implements Runnable {
    private final Market market;
    private final ExchangeApi apiClient;
    private final CandlestickProcessor processorService;
    private final SqlClientService sqlClientService;
    private final WatcherTaskCallback watcherTaskCallback;
    private final CalendarUtils calendar;

    public HistoricDataRetrieverTask(Market market,
                                     ExchangeApi apiClient,
                                     CandlestickProcessor processorService,
                                     SqlClientService sqlClientService,
                                     WatcherTaskCallback watcherTaskCallback,
                                     CalendarUtils calendar) {
        this.market = market;
        this.apiClient = apiClient;
        this.processorService = processorService;
        this.sqlClientService = sqlClientService;
        this.watcherTaskCallback = watcherTaskCallback;
        this.calendar = calendar;
    }

    @Override
    public void run() {
        try {
            Thread.currentThread().setName(market.getMarketCode());

            sqlClientService.createTableIfNotExists(market);

            LocalDateTime start = getLastCandleTimestampOrDefault();
            LocalDateTime beginning = start;
            LocalDateTime end = calendar.getCurrentCandleTimestampForTimeframe(market);
            String rawMarketData;
            List<CandlestickEntity> candlesticks;

            while (dbNotUpdated(start, end)) {
                rawMarketData = apiClient.fetchPublicMarketData(market.getCandlestickURL(start, end));
                candlesticks = processorService.process(market, rawMarketData).join();
                sqlClientService.insertIntoTable(market, candlesticks);
                start = candlesticks.getLast().getTimestamp().toLocalDateTime();
                end = calendar.getCurrentCandleTimestampForTimeframe(market);
            }
            log.info("thread=HistoricDataRetrieverTask, message='Historic Candlesticks inserted', beginning='{}', ending='{}', market.code='{}'", beginning, end, market.getMarketCode());

            watcherTaskCallback.onTaskCompleted(market);
        } catch (Exception e){
            log.error("thread=HistoricDataRetrieverTask, market.code='{}', exception='{}'", market.getMarketCode(), e);
            log.error("Stack trace:\n{}", getStackTraceAsString(e));
            Thread.currentThread().interrupt();
        }

    }

    private LocalDateTime getLastCandleTimestampOrDefault() {
        LocalDateTime start = Instant.EPOCH.atOffset(ZoneOffset.UTC).toLocalDateTime(); // Min date
        Optional<CandlestickEntity> lastCandlestick = sqlClientService.getLastCandlestick(market);
        if (lastCandlestick.isPresent()) {
            start = lastCandlestick.get().getTimestamp().toLocalDateTime();
        }
        return start;
    }

    private boolean dbNotUpdated(LocalDateTime start, LocalDateTime end) {
        return start.isBefore(end);
    }

    private String getStackTraceAsString(Exception e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}
