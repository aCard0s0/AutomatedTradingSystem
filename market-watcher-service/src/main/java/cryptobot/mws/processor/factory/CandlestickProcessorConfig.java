package cryptobot.mws.processor.factory;

import cryptobot.cml.enums.Exchange;
import cryptobot.mws.processor.CandlestickProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CandlestickProcessorConfig {
    private final CandlestickProcessorFactory processorFactory;

    public CandlestickProcessorConfig(CandlestickProcessorFactory processorFactory) {
        this.processorFactory = processorFactory;
    }

    @Bean
    public CandlestickProcessorMap candlestickProcessorMap() {
        Map<Exchange, CandlestickProcessor> processorMap = Map.ofEntries(
                processorFactory.createKrakenProcessorEntry(),
                processorFactory.createBinanceProcessorEntry()
        );
        return new CandlestickProcessorMap(processorMap);
    }
}
