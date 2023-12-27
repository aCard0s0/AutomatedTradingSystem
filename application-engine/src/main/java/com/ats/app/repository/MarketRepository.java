package com.ats.app.repository;

import com.ats.app.domain.MarketTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends MongoRepository<MarketTable, String> {
}
