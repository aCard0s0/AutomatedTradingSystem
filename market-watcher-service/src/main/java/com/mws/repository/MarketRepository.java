package com.mws.repository;

import com.mws.domain.MarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<MarketEntity, Long> {
}
