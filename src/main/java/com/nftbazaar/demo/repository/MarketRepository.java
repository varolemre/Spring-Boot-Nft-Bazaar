package com.nftbazaar.demo.repository;

import com.nftbazaar.demo.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<Market,Long> {

    Market findByNftId(Long nftId);
}
