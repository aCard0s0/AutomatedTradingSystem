package cryptobot.mws.processor;

import cryptobot.cml.model.Market;
import cryptobot.mws.domain.CandlestickEntity;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 *  BUG:
 *  Existem candles iguais com o mesmo timestamp
 *  select * from candlestick_partition_binance_eurbtc_1m where timestamp = '2020-01-03 16:19:00.000000';
 */
@Log4j2
public class BinanceCandlestickProcessor implements CandlestickProcessor {
    // TODO: The response can be parsed using a JSON parser
    @Override
    public CompletableFuture<List<CandlestickEntity>> process(Market market, String candlestickResponse) {
        List<CandlestickEntity> candlesticks = new ArrayList<>();

        String[] candles = candlestickResponse.split("\\[");
        for (String candle : candles) {
            if (!candle.isEmpty()) {
                CandlestickEntity candlestick = buildCandlestick(market, candle);
                candlesticks.add(candlestick);
            }
        }

        log.info("operation=process, message='Candlesticks processed' market={}, candlesticks.size={}", market, candlesticks.size());

        return CompletableFuture.completedFuture(candlesticks);
    }

    private CandlestickEntity buildCandlestick(Market market, String candle) {
        String[] data = candle.split(",");
         Timestamp timestamp = new Timestamp(Long.parseLong(data[0].replaceAll("[^0-9]", "")));
        return CandlestickEntity.builder()
                .pair(market.getPairStr())
                .timeframe(market.getTimeframeStr())
                .timestamp(timestamp)
                .open(new BigDecimal(data[1].replaceAll("[^0-9.]", "")))
                .high(new BigDecimal(data[2].replaceAll("[^0-9.]", "")))
                .low(new BigDecimal(data[3].replaceAll("[^0-9.]", "")))
                .close(new BigDecimal(data[4].replaceAll("[^0-9.]", "")))
                .volume(new BigDecimal(data[5].replaceAll("[^0-9.]", "")))
                .build();
    }
}
