package cryptobot.mws.service.db;

import cryptobot.cml.model.Market;
import cryptobot.mws.domain.CandlestickEntity;

import java.util.Collection;
import java.util.Optional;

/**
 * Service to interact with the database.
 */
public interface SqlClientService {
    /**
     * Creates a table for the given market if it does not exist.
     * @param market - the market for which the table will be created.
     */
    void createTableIfNotExists(Market market);

    /**
     * Inserts the given candlesticks into the table for the given market.
     * @param market - the market for which the candlesticks will be inserted.
     * @param candlesticks - the candlesticks to be inserted.
     */
    void insertIntoTable(Market market, Collection<CandlestickEntity> candlesticks);

    /**
     * Returns the last candlestick for the given market.
     * @param market - the market for which the last candlestick will be returned.
     * @return the last candlestick for the given market if it exists or an empty optional otherwise.
     */
    Optional<CandlestickEntity> getLastCandlestick(Market market);
}
