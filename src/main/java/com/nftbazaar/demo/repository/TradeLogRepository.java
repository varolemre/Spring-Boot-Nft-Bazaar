package com.nftbazaar.demo.repository;

import com.nftbazaar.demo.entity.TradeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeLogRepository extends JpaRepository<TradeLog,Long> {
}
