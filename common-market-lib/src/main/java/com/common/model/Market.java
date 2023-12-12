package com.common.model;

import com.common.enums.Exchange;
import com.common.stategy.interval.TimeframeStrategy;
import com.common.stategy.pair.PairStrategy;
import com.common.stategy.url.UrlStrategy;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Market {
    @Getter
    private final Exchange exchange;
    private final PairStrategy pair;
    private final TimeframeStrategy interval;
    private final UrlStrategy url;

    public String getCronExpression() {
        return interval.getCronExpression();
    }

    public String getCandlestickURL() {
        return url.buildCandlestickUrl(pair.getExchangePair(), interval.getExchangeTimeframe());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(exchange, pair, interval);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Market market)) return false;
        return exchange == market.exchange && pair == market.pair && interval == market.interval;
    }

    @Override
    public String toString() {
        return String.format("Market{exchangeHouse=%s, pair=%s, interval=%s}", exchange, pair.getPair(), interval.getTimeframe());
    }

}
