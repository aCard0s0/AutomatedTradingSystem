package cryptobot.aes.service;

import cryptobot.aes.domain.MarketTable;
import cryptobot.aes.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {
    private final MarketRepository marketRepository;

    @Autowired
    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    public List<MarketTable> getAllCandles() {
        return marketRepository.findAll();
    }

    public MarketTable saveUser(MarketTable candle) {
        return marketRepository.save(candle);
    }

    public void saveAll(List<MarketTable> users) {
        marketRepository.saveAll(users);
    }
}
