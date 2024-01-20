package com.mws.domain;

import com.google.common.base.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(
    name = "market"
)
public class MarketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "exchange", nullable = false)
    private String exchange;
    @Column(name = "pair", nullable = false)
    private String pair;
    @Column(name = "timeframe", nullable = false)
    private String timeframe;
    @Column(name = "active", nullable = false)
    private boolean active;

    public MarketEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarketEntity that)) return false;
        return Objects.equal(id, that.id) &&
                Objects.equal(exchange, that.exchange) &&
                Objects.equal(pair, that.pair) &&
                Objects.equal(timeframe, that.timeframe) &&
                active == that.active;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, exchange, pair, timeframe, active);
    }

    @Override
    public String toString() {
        return "MarketTable{id=%d, exchange='%s', pair='%s', timeframe='%s', active=%s}"
                .formatted(id, exchange, pair, timeframe, active);
    }
}
