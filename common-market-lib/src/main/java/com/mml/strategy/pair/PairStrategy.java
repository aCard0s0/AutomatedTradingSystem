package com.mml.strategy.pair;

import com.mml.enums.Pair;

/**
 *
 */
public interface PairStrategy {
    /**
     * @return
     */
    Pair getPair();

    /**
     * @return
     */
    String getExchangePair();
}
