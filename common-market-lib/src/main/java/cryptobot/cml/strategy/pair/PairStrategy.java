package cryptobot.cml.strategy.pair;

import cryptobot.cml.enums.Pair;

/**
 *
 */
public interface PairStrategy {
    /**
     * @return the pair definition used by AutomatedTradingSystem
     */
    Pair getPair();

    /**
     * @return the pair definition for the exchange
     */
    String getExchangePair();
}
