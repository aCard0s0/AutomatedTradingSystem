package com.mws.processor.factory;

import com.cml.enums.Exchange;
import com.mws.processor.CandlestickProcessor;

import java.util.Map;

public class CandlestickProcessorMap {
    private final Map<Exchange, CandlestickProcessor> processorMap;

    public CandlestickProcessorMap(Map<Exchange, CandlestickProcessor> processorMap) {
        this.processorMap = processorMap;
    }

    public CandlestickProcessor getProcessor(Exchange type) {
        return processorMap.get(type);
    }
}
