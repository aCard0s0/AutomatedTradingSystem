package cryptobot.mws.httppool.factory;

import cryptobot.cml.enums.Exchange;
import cryptobot.mws.httppool.ExchangeApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ExchangeApiConfig {
    private final ExchangeApiFactory exchangeApiFactory;

    public ExchangeApiConfig(ExchangeApiFactory exchangeApiFactory) {
        this.exchangeApiFactory = exchangeApiFactory;
    }

    @Bean
    public ExchangeApiMap exchangeApiMap() {
        Map<Exchange, ExchangeApi> exchangeApiMap = Map.ofEntries(
                exchangeApiFactory.createKrakenApiClientEntry(),
                exchangeApiFactory.createBinanceApiClientEntry()
        );
        return new ExchangeApiMap(exchangeApiMap);
    }
}
