package cryptobot.mws.task.runnable;

import cryptobot.cml.model.Market;
import cryptobot.mws.httppool.ExchangeApi;
import cryptobot.mws.domain.CandlestickEntity;
import cryptobot.mws.service.db.SqlClientService;
import cryptobot.mws.processor.CandlestickProcessor;
import lombok.extern.log4j.Log4j2;

import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for:
 *      1. fetching the last candlestick from the database,
 *      2. fetching the raw market data from the exchange API,
 *      3. processing the raw market data,
 *      4. inserting the candlesticks into the database.
 *  If the database does not contain any candlestick, the task will not be executed.
 */
@Log4j2
public class MarketWatcherTask implements Runnable {
    private final Market market;
    private final ExchangeApi apiClient;
    private final CandlestickProcessor processorService;
    private final SqlClientService sqlClientService;
    private List<CandlestickEntity> candlesticks;

    public MarketWatcherTask(Market market,
                             ExchangeApi apiClient,
                             CandlestickProcessor processorService,
                             SqlClientService sqlClientService
    ) {
        this.market = market;
        this.apiClient = apiClient;
        this.processorService = processorService;
        this.sqlClientService = sqlClientService;
        this.candlesticks = Collections.emptyList();
    }

    @Override
    public void run() {
        try {
            Thread.currentThread().setName(market.getMarketCode());

            sqlClientService.getLastCandlestick(market)
                .ifPresentOrElse(
                    candlestick -> {
                        String rawMarketData = apiClient.fetchPublicMarketData(generateApiUrl(candlestick));
                        candlesticks = processorService.process(market, rawMarketData).join();
                        sqlClientService.insertIntoTable(market, candlesticks);
                        log.info("thread=MarketWatcherTask, message='Candlestick inserted', market.code='{}', candlestick.size='{}'", market.getMarketCode(), candlesticks.size());
                        candlesticks.clear();
                    },
                    () -> log.warn("thread=MarketWatcherTask, message='No candlestick found for market', market.code='{}'", market.getMarketCode()));

        } catch (Exception e){
            log.error("thread=MarketWatcherTask, market.code='{}', exception='{}'", market.getMarketCode(), e);
            log.error("Stack trace:\n{}", getStackTraceAsString(e));
            Thread.currentThread().interrupt();
            log.info("thread=MarketWatcherTask, message='Thread interrupted', name='{}', market.code='{}'", Thread.currentThread().getName(), market.getMarketCode());
        }
    }

    private String generateApiUrl(CandlestickEntity candle) {
        return market.getCandlestickURL(candle.getTimestamp().toLocalDateTime());
    }

    private String getStackTraceAsString(Exception e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}
