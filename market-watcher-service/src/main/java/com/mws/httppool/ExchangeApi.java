package com.mws.httppool;

/**
 *  Interface for the Exchange API Client
 *  This interface is used to define the methods that will be used to fetch data from the exchange API.
 *
 */
public interface ExchangeApi {
    /**
     * Fetches public market data from the exchange API
     * @param url The URL to fetch the data from
     * @return The response from the exchange API
     */
    String fetchPublicMarketData(String url);
}
