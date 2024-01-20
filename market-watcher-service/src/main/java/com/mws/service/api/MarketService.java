package com.mws.service.api;

import com.cml.factory.MarketFactory;
import com.cml.model.Market;
import com.mws.domain.request.MarketInput;
import com.mws.domain.MarketEntity;
import com.mws.mapper.MarketMapper;
import com.mws.repository.MarketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class MarketService {
    private final MarketRepository marketRepository;
    private final MarketMapper marketMapper;
    private final TaskService taskService;

    public MarketService(MarketRepository marketRepository, MarketMapper marketMapper, TaskService taskService) {
        this.marketRepository = marketRepository;
        this.marketMapper = marketMapper;
        this.taskService = taskService;
    }

    public MarketEntity createMarket(MarketInput newMarket) {
        MarketEntity market = marketMapper.marketInputToEntity(newMarket);
        return marketRepository.save(market);
    }

    public Optional<MarketEntity> getMarketById(Long id) {
        return marketRepository.findById(id);
    }

    public Optional<MarketEntity> updateMarket(Long id, MarketInput newMarket) {
        Optional<MarketEntity> optOldMarket = marketRepository.findById(id);
        if (optOldMarket.isEmpty()) {
            return optOldMarket;
        }

        MarketEntity oldMarket = optOldMarket.get();
        oldMarket.setExchange(newMarket.exchange());
        oldMarket.setPair(newMarket.pair());
        oldMarket.setTimeframe(newMarket.timeframe());
        oldMarket.setActive(newMarket.active());
        return Optional.of(marketRepository.save(oldMarket));
    }

    public Optional<List<MarketEntity>> getAllMarketsByParameters(String exchange, String pair, String timeframe, Boolean active) {
        List<Predicate<MarketEntity>> allPredicates = new ArrayList<>();

        if (isNotNullOrEmpty(exchange)) {
            allPredicates.add(market -> market.getExchange().equalsIgnoreCase(exchange));
        }
        if (isNotNullOrEmpty(pair)) {
            allPredicates.add(market -> market.getPair().equalsIgnoreCase(pair));
        }
        if (isNotNullOrEmpty(timeframe)) {
            allPredicates.add(market -> market.getTimeframe().equalsIgnoreCase(timeframe));
        }
        if (active != null) {
            allPredicates.add(market -> market.isActive() == active);
        }

        List<MarketEntity> entities = marketRepository.findAll().stream()
                .filter(allPredicates.stream().reduce(x -> true, Predicate::and))
                .toList();

        if (entities.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(entities);
    }

    public List<Market> getAllActiveMarkets() {
        return marketRepository.findAll().stream()
                .filter(MarketEntity::isActive)
                .map(this::generateMarket)
                .toList();
    }

    public Optional<MarketEntity> activateMarket(Long id, String task) {
        Optional<MarketEntity> marketEntity = activateMarketEntity(id);
        if (marketEntity.isPresent() && isNotNullOrEmpty(task) && task.equalsIgnoreCase("start")) {
            taskService.startTask(marketMapper.entityToMarket(marketEntity.get()));
        }
        return marketEntity;
    }

    public Optional<MarketEntity> deactivateMarket(Long id, String task) {
        Optional<MarketEntity> marketEntity = deactivateMarketEntity(id);
        if (marketEntity.isPresent() && isNotNullOrEmpty(task) && task.equalsIgnoreCase("stop")) {
            taskService.stopTask(marketMapper.entityToMarket(marketEntity.get()));
        }
        return marketEntity;
    }

    private boolean isNotNullOrEmpty(String parameter) {
        return parameter != null && !parameter.isEmpty();
    }

    private Market generateMarket(MarketEntity marketTable) {
        return MarketFactory.buildMarket(marketTable.getExchange(), marketTable.getPair(), marketTable.getTimeframe());
    }

    private Optional<MarketEntity> activateMarketEntity(Long id) {
        return marketRepository.findById(id)
                .map(market -> {
                    market.setActive(true);
                    return marketRepository.save(market);
                });
    }

    private Optional<MarketEntity> deactivateMarketEntity(Long id) {
        return marketRepository.findById(id)
                .map(market -> {
                    market.setActive(false);
                    return marketRepository.save(market);
                });
    }
}
