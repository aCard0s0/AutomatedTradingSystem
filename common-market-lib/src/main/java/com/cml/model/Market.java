package com.cml.model;

import com.cml.enums.Exchange;
import com.cml.enums.Pair;
import com.cml.enums.Timeframe;
import com.cml.strategy.timeframe.TimeframeStrategy;
import com.cml.strategy.pair.PairStrategy;
import com.cml.strategy.url.UrlStrategy;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public final class Market {
    @Getter
    private final Exchange exchange;
    private final PairStrategy pair;
    private final TimeframeStrategy timeframe;
    private final UrlStrategy url;

    public String getMarketId() {
        return "%s_%s_%s".formatted(exchange.getExchange(), getPairStr(), getTimeframeStr());
    }

    public String getTableName() {
        return "candlestick_partition_"+ getMarketId();
    }

    public Pair getPair() {
        return pair.getPair();
    }

    public String getPairStr() {
        return pair.getPair().getPair();
    }

    public Timeframe getTimeframe() {
        return timeframe.getTimeframe();
    }

    public String getTimeframeStr() {
        return timeframe.getTimeframe().getTimeframe();
    }

    public String getCandlestickURL(LocalDateTime start) {
        return url.buildCandlestickUrl(pair.getExchangePair(), timeframe.getExchangeTimeframe(), start);
    }

    public String getCandlestickURL(LocalDateTime start, LocalDateTime end) {
        return url.buildCandlestickUrl(pair.getExchangePair(), timeframe.getExchangeTimeframe(), start, end);
    }

    public String getExchangePair() {
        return pair.getExchangePair();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(exchange, pair, timeframe);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Market that = (Market) o;
        return Objects.equal(this.exchange, that.exchange)
                && Objects.equal(this.pair, that.pair)
                && Objects.equal(this.timeframe, that.timeframe);
    }

    @Override
    public String toString() {
        return String.format("Market{%s, %s, %s}", exchange, pair.getPair(), timeframe.getTimeframe());
    }

}
