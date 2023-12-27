package com.ats.app.controller;

import com.ats.app.domain.MarketTable;
import com.ats.app.service.MarketService;
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
