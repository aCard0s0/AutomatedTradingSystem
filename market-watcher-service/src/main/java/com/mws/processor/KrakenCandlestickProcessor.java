package com.mws.processor;

import com.cml.model.Market;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mws.domain.CandlestickEntity;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Log4j2
public class KrakenCandlestickProcessor implements CandlestickProcessor {
    @Override
    public CompletableFuture<List<CandlestickEntity>> process(Market market, String candlestickResponse) {
        List<CandlestickEntity> candlesticks = new ArrayList<>();

        try {
            Map<String, Object> responseMap = parseCandlestickResponse(candlestickResponse);
            List<List<Object>> candleData = extractCandleData(market, responseMap);
            candleData.stream()
                    .map(candle -> buildCandlestick(market, candle))
                    .forEach(candlesticks::add);

        } catch (IOException e) {
            log.error("operation=process, status=failed, message={}", e.getMessage());
            throw new RuntimeException("Failed to process candlesticks.");
        }

        log.debug("operation=process, message='Candlesticks processed', market.code='{}', candlestick.size='{}'", market.getMarketCode(), candlesticks.size());
        return CompletableFuture.completedFuture(candlesticks);
    }

    private Map<String, Object> parseCandlestickResponse(String candlestickResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(candlestickResponse, new TypeReference<>() {});
    }

    private List<List<Object>> extractCandleData(Market market, Map<String, Object> responseMap) {
        Map<String, Object> result = (Map<String, Object>) responseMap.get("result");
        return (List<List<Object>>) result.get(market.getExchangePair());
    }

    private CandlestickEntity buildCandlestick(Market market, List<Object> candle) {
        return CandlestickEntity.builder()
                .pair(market.getPairStr())
                .timeframe(market.getTimeframeStr())
                .timestamp(new Timestamp(Long.parseLong(String.valueOf(candle.get(0))) * 1000))
                .open(new BigDecimal(String.valueOf(candle.get(1))))
                .high(new BigDecimal(String.valueOf(candle.get(2))))
                .low(new BigDecimal(String.valueOf(candle.get(3))))
                .close(new BigDecimal(String.valueOf(candle.get(4))))
                .volume(new BigDecimal(String.valueOf(candle.get(6))))
                .build();
    }
}
