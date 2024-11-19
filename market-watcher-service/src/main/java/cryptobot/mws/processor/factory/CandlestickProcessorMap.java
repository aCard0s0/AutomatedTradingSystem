package cryptobot.mws.processor.factory;

import cryptobot.cml.enums.Exchange;
import cryptobot.mws.processor.CandlestickProcessor;

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
