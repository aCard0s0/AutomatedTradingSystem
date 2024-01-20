package com.mws.mapper;

import com.cml.factory.MarketFactory;
import com.cml.model.Market;
import com.mws.domain.request.MarketInput;
import com.mws.domain.MarketEntity;
import com.mws.domain.request.TaskInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MarketMapper {
    @Mapping(ignore = true, target = "id")
    MarketEntity marketInputToEntity(MarketInput inputMarket);

    default Market entityToMarket(MarketEntity entityMarket) {
        return MarketFactory.buildMarket(entityMarket.getExchange(),
                entityMarket.getPair(),
                entityMarket.getTimeframe());
    }

    default Market taskInputToMarket(TaskInput taskIn) {
        String[] market = taskIn.marketCode().split("_");
        return MarketFactory.buildMarket(market[0], market[1], market[2]);
    }
}
