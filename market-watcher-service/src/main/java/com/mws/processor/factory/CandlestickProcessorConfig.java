package com.mws.processor.factory;

import com.cml.enums.Exchange;
import com.mws.processor.BinanceCandlestickProcessor;
import com.mws.processor.CandlestickProcessor;
import com.mws.processor.KrakenCandlestickProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CandlestickProcessorConfig {
    private final CandlestickProcessorFactory processorFactory;

    public CandlestickProcessorConfig(CandlestickProcessorFactory processorFactory) {
        this.processorFactory = processorFactory;
    }

    @Bean
    public CandlestickProcessorMap candlestickProcessorMap() {
        Map<Exchange, CandlestickProcessor> processorMap = Map.ofEntries(
                processorFactory.createKrakenProcessorEntry(),
                processorFactory.createBinanceProcessorEntry()
        );
        return new CandlestickProcessorMap(processorMap);
    }
}
