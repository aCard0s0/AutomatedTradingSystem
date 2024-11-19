package cryptobot.aes.repository;

import cryptobot.aes.domain.MarketTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<MarketTable, String> {
}
