package com.stock.stock.monitoring.reository;

import com.stock.stock.monitoring.entity.StockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockData, Long> {

    Optional<StockData> findBySymbol(String symbol);
}
