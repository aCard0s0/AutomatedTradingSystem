package com.mws.controller;


import com.mws.domain.MarketEntity;
import com.mws.domain.request.MarketInput;
import com.mws.service.api.MarketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v0/market")
public class MarketController {
    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @PostMapping
    public ResponseEntity<MarketEntity> createMarketEntity(@RequestBody MarketInput newMarket) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(marketService.createMarket(newMarket));
    }

    @GetMapping
    public ResponseEntity<List<MarketEntity>> getAllMarketEntitiesByParameters(@RequestParam(name = "exchange", required = false) String exchange,
                                                                               @RequestParam(name = "pair", required = false) String pair,
                                                                               @RequestParam(name = "timeframe", required = false) String timeframe,
                                                                               @RequestParam(name = "active", required = false) Boolean active) {
        return marketService.getAllMarketsByParameters(exchange, pair, timeframe, active)
                .map(entities -> ResponseEntity.status(HttpStatus.OK).body(entities))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarketEntity> getMarketEntityById(@PathVariable Long id) {
        return marketService.getMarketById(id)
                .map(entity -> ResponseEntity.status(HttpStatus.OK).body(entity))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarketEntity> updateMarketEntity(@PathVariable Long id, @RequestBody MarketInput newMarket) {
        return marketService.updateMarket(id, newMarket)
                .map(entity -> ResponseEntity.status(HttpStatus.OK).body(entity))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<MarketEntity> activateMarketEntity(@PathVariable Long id, @RequestParam String task) {
        return marketService.activateMarket(id, task)
                .map(entity -> ResponseEntity.status(HttpStatus.OK).body(entity))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<MarketEntity> deactivateMarketEntity(@PathVariable Long id, @RequestParam String task) {
        return marketService.deactivateMarket(id, task)
                .map(entity -> ResponseEntity.status(HttpStatus.OK).body(entity))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
