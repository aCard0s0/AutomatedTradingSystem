package cryptobot.mws.httppool.factory;

import cryptobot.cml.enums.Exchange;
import cryptobot.mws.httppool.ExchangeApi;
import cryptobot.mws.httppool.GenericApiClient;
import org.apache.http.client.HttpClient;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;

@Service
public class ExchangeApiFactory {
    private final HttpClient httpClient;

    public ExchangeApiFactory(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public AbstractMap.SimpleImmutableEntry<Exchange, ExchangeApi> createKrakenApiClientEntry() {
        return new AbstractMap.SimpleImmutableEntry<>(Exchange.KRAKEN, new GenericApiClient(httpClient));
    }

    public AbstractMap.SimpleImmutableEntry<Exchange, ExchangeApi> createBinanceApiClientEntry() {
        return new AbstractMap.SimpleImmutableEntry<>(Exchange.BINANCE, new GenericApiClient(httpClient));
    }
}
