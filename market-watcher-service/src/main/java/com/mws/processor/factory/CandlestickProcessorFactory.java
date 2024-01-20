package com.mws.processor.factory;

import com.cml.enums.Exchange;
import com.mws.processor.BinanceCandlestickProcessor;
import com.mws.processor.CandlestickProcessor;
import com.mws.processor.KrakenCandlestickProcessor;
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
