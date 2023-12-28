package com.cml.strategy.pair;

import com.cml.enums.Pair;

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
