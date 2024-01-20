package com.mws.httppool.factory;

import com.cml.enums.Exchange;
import com.mws.httppool.ExchangeApi;

import java.util.Map;

public class ExchangeApiMap {
    private final Map<Exchange, ExchangeApi> exchangeApiMap;

    public ExchangeApiMap(Map<Exchange, ExchangeApi> exchangeApiMap) {
        this.exchangeApiMap = exchangeApiMap;
    }

    public ExchangeApi getApiClient(Exchange type) {
        return exchangeApiMap.get(type);
    }
}
