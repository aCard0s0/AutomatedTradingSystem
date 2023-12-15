package com.common.model;

import com.common.enums.Exchange;
import com.common.enums.Timeframe;
import com.common.strategy.timeframe.TimeframeStrategy;
import com.common.strategy.pair.PairStrategy;
import com.common.strategy.url.UrlStrategy;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@RequiredArgsConstructor
public final class Market {
    @Getter
    private final Exchange exchange;
    private final PairStrategy pair;
    private final TimeframeStrategy timeframe;
    private final UrlStrategy url;

    public String getMarketId() {
        return "%s-%s-%s".formatted(exchange.getExchange(), getPair(), getTimeframe());
    }

    public String getTableName() {
        return "candlestick_partition_"+ getMarketId().replace("-", "_");
    }

    public String getPair() {
        return pair.toString();
    }

    public Timeframe getTimeframe() {
        return timeframe.getTimeframe();
    }

    public String getTimeframeStr() {
        return timeframe.getTimeframe().getTimeframe();
    }

    public String getCandlestickURL() {
        return url.buildCandlestickUrl(pair.getExchangePair(), timeframe.getExchangeTimeframe());
    }

    public String getCandlestickURL(Timestamp start) {
        return url.buildCandlestickUrl(pair.getExchangePair(), timeframe.getExchangeTimeframe(), start);
    }

    public String getCandlestickURL(Timestamp start, Timestamp end) {
        return url.buildCandlestickUrl(pair.getExchangePair(), timeframe.getExchangeTimeframe(), start, end);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(exchange, pair, timeframe);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Market market)) return false;
        return exchange == market.exchange && pair == market.pair && timeframe == market.timeframe;
    }

    @Override
    public String toString() {
        return String.format("Market{exchange=%s, pair=%s, interval=%s}", exchange, pair.getPair(), timeframe.getTimeframe());
    }

}
