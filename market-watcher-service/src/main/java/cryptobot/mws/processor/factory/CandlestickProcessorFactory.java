package cryptobot.mws.processor.factory;

import cryptobot.cml.enums.Exchange;
import cryptobot.mws.processor.BinanceCandlestickProcessor;
import cryptobot.mws.processor.CandlestickProcessor;
import cryptobot.mws.processor.KrakenCandlestickProcessor;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;

@Service
public class CandlestickProcessorFactory {
    public SimpleImmutableEntry<Exchange, CandlestickProcessor> createKrakenProcessorEntry() {
        return new SimpleImmutableEntry<>(Exchange.KRAKEN, new KrakenCandlestickProcessor());
    }

    public SimpleImmutableEntry<Exchange, CandlestickProcessor> createBinanceProcessorEntry() {
        return new AbstractMap.SimpleImmutableEntry<>(Exchange.BINANCE, new BinanceCandlestickProcessor());
    }
}
