package com.aes.service;

import com.aes.domain.MarketTable;
import com.aes.repository.MarketRepository;
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
