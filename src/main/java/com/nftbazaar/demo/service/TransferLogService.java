package com.nftbazaar.demo.service;

import com.nftbazaar.demo.entity.TransferLog;
import com.nftbazaar.demo.entity.User;
import com.nftbazaar.demo.repository.TransferLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TransferLogService {
    private final TransferLogRepository transferLogRepository;

    public TransferLog saveTransferLog(User user, String walletId, Long amount, String status){
        TransferLog transferLog = new TransferLog();
        transferLog.setUser(user);
        transferLog.setStatus(status);
        transferLog.setAmount(amount);
        transferLog.setUtilDate(java.time.LocalDateTime.now());
        Instant inst = Instant.now();
        transferLog.setUtilTime(Date.from(inst));
        transferLog.setWallet(walletId);
        return transferLogRepository.save(transferLog);

    }
}
