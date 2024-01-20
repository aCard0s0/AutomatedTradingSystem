package com.mws.processor;

import com.cml.enums.Exchange;
import com.cml.enums.Pair;
import com.cml.enums.Timeframe;
import com.cml.factory.MarketFactory;
import com.cml.model.Market;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mws.domain.CandlestickEntity;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mws.utils.CandlestickMockGenerator.readKrakenRawCandlestickData;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertThrows;

public class KrakenCandlestickProcessorTest {
    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private KrakenCandlestickProcessor victim;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void process_ValidData_ReturnsCandlesticks() throws IOException {
        Market market = MarketFactory.buildMarket(Exchange.KRAKEN, Pair.EUR_BTC, Timeframe.ONE_HOUR);
        Map<String, Object> responseMap = new HashMap<>();
        when(objectMapper.readValue(anyString(), any(JavaType.class))).thenReturn(responseMap);
        List<List<Object>> candleData = Collections.emptyList();
        responseMap.put("result", Collections.singletonMap(market.getExchangePair(), candleData));

        List<CandlestickEntity> candlesticks = victim.process(market, readKrakenRawCandlestickData()).join();

        assertNotNull(candlesticks);
    }

    @Test(expectedExceptions = RuntimeException.class)
    void process_InvalidData_ThrowsException() throws IOException {
        Market market = MarketFactory.buildMarket(Exchange.KRAKEN, Pair.EUR_BTC, Timeframe.ONE_HOUR);
        String candlestickResponse = "invalid response";

        when(objectMapper.readValue(anyString(), any(JavaType.class))).thenThrow(IOException.class);

        assertThrows(RuntimeException.class, () -> victim.process(market, candlestickResponse).join());
    }
}
