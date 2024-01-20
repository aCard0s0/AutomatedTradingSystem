package com.mws.utils;

import com.mws.domain.CandlestickEntity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CandlestickMockGenerator {
    public static CandlestickEntity buildMockCandlestick() {
        return CandlestickEntity.builder()
                .pair("eurbtc")
                .timestamp(new Timestamp(Instant.now().toEpochMilli()))
                .open(new BigDecimal("1000.0"))
                .high(new BigDecimal("2000.0"))
                .low(new BigDecimal("500.0"))
                .close(new BigDecimal("1500.0"))
                .volume(new BigDecimal("100.0"))
                .build();
    }

    public static String readKrakenRawCandlestickData() {
        return loadRawCandlestickData("kraken/kraken_response_btceur_1h.json");
    }

    public static List<CandlestickEntity> readProcessedCandlestickData() {
        return loadProcessedCandlestickData("processed_candlesticks_btceur_1h.csv");
    }

    private static String loadRawCandlestickData(String path) {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))
                .lines()
                .collect(Collectors.joining());
    }

    private static List<CandlestickEntity> loadProcessedCandlestickData(String path) {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))
                .lines()
                .map(CandlestickMockGenerator::buildMockCandlestick)
                .collect(Collectors.toList());
    }

    private static CandlestickEntity buildMockCandlestick(String line) {
        String[] split = line.split(",");
        return CandlestickEntity.builder()
                .pair(split[0])
                .timeframe(split[1])
                .open(new BigDecimal(split[2]))
                .high(new BigDecimal(split[3]))
                .low(new BigDecimal(split[4]))
                .close(new BigDecimal(split[5]))
                .volume(new BigDecimal(split[6]))
                .timestamp(Timestamp.valueOf(split[7]))
                .build();
    }
}
