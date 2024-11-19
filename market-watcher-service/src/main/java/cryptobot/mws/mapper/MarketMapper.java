package cryptobot.mws.mapper;

import cryptobot.cml.factory.MarketFactory;
import cryptobot.cml.model.Market;
import cryptobot.mws.domain.request.MarketInput;
import cryptobot.mws.domain.MarketEntity;
import cryptobot.mws.domain.request.TaskInput;
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
