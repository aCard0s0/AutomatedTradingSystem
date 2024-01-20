package com.mws.domain;

import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CandlestickEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pair", nullable = false)
    private String pair;
    @Column(name = "timeframe", nullable = false)
    private String timeframe;
    @Column(name = "open", nullable = false)
    private BigDecimal open;
    @Column(name = "high", nullable = false)
    private BigDecimal high;
    @Column(name = "low", nullable = false)
    private BigDecimal low;
    @Column(name = "close", nullable = false)
    private BigDecimal close;
    @Column(name = "volume", nullable = false)
    private BigDecimal volume;
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    public CandlestickEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandlestickEntity that)) return false;
        return Objects.equal(id, that.id) &&
                Objects.equal(pair, that.pair) &&
                Objects.equal(timeframe, that.timeframe) &&
                Objects.equal(open, that.open) &&
                Objects.equal(high, that.high) &&
                Objects.equal(low, that.low) &&
                Objects.equal(close, that.close) &&
                Objects.equal(volume, that.volume) &&
                Objects.equal(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, pair, timeframe, open, high, low, close, volume, timestamp);
    }

    @Override
    public String toString() {
        return "Candlestick{id=%d, pair='%s', timeframe='%s', open=%s, high=%s, low=%s, close=%s, volume=%s, timestamp=%s}"
                .formatted(id, pair, timeframe, open, high, low, close, volume, timestamp);
    }
}

