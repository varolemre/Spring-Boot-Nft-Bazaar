package com.nftbazaar.demo.repository;

import com.nftbazaar.demo.entity.TransferLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferLogRepository extends JpaRepository<TransferLog, Long> {
}
