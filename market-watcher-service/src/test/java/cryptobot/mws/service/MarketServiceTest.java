package cryptobot.mws.service;

import cryptobot.cml.model.Market;
import cryptobot.mws.domain.MarketEntity;
import cryptobot.mws.repository.MarketRepository;
import cryptobot.mws.service.api.MarketService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

import static cryptobot.mws.utils.MarketTableMockGenerator.buildMockMarketTableWith;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Ignore
@SpringBootTest
public class MarketServiceTest {
    @InjectMocks
    private MarketService victim;
    @Mock
    private MarketRepository marketRepo;

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllActiveMarkets() {
        List<MarketEntity> marketTables = List.of(
                buildMockMarketTableWith(1, true),
                buildMockMarketTableWith(2, false),
                buildMockMarketTableWith(3, true),
                buildMockMarketTableWith(4, true),
                buildMockMarketTableWith(5, false)
        );

        when(marketRepo.findAll()).thenReturn(marketTables);
        //when(marketMapperTOELIMINATE.mapToMarket(marketTables.get(anyInt()))).thenReturn(buildMockMarket());

        List<Market> activeMarkets = victim.getAllActiveMarkets();

        assertNotNull(activeMarkets);
        assertEquals(3, activeMarkets.size());
    }
}
