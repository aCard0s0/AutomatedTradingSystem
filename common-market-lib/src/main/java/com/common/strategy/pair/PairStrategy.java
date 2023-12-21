package com.common.strategy.pair;

import com.common.enums.Pair;

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
