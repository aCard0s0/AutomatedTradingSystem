package cryptobot.aes.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "market")
public class MarketTable {
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

    public MarketTable() {
    }

    @Override
    public String toString() {
        return "MarketTable{id=%d, exchange='%s', pair='%s', timeframe='%s', active=%s}"
                .formatted(id, exchange, pair, timeframe, active);
    }
}
