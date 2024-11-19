package cryptobot.aes.controller;

import cryptobot.aes.domain.MarketTable;
import cryptobot.aes.service.MarketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MarketController {
    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping("/markets")
    public List<MarketTable> getMarkets() {
        return marketService.getAllCandles();
    }
}
