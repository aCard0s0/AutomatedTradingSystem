package com.mws.processor;

import com.cml.model.Market;
import com.mws.domain.CandlestickEntity;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Interface for candlestick processors
 * Each exchange will have its own implementation
 */
public interface CandlestickProcessor {
    /**
     * Process the candlestick response and return a list of candlesticks
     *
     * @param market              - market to process
     * @param candlestickResponse - raw response from the exchange
     * @return - list of candlesticks
     */
    @Async
    CompletableFuture<List<CandlestickEntity>> process(Market market, String candlestickResponse);
}
