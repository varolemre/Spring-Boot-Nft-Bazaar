package com.nftbazaar.demo.service;

import com.nftbazaar.demo.entity.Nft;
import com.nftbazaar.demo.entity.TradeLog;
import com.nftbazaar.demo.entity.User;
import com.nftbazaar.demo.repository.TradeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TradeLogService {

    private final TradeLogRepository tradeLogRepository;


    public TradeLog saveTradeLog(User user, Nft nft, String status){
        TradeLog tradeLog = new TradeLog();
       tradeLog.setUser(user);
        tradeLog.setNft(nft);
        tradeLog.setStatus(status);
        tradeLog.setUtilDate(java.time.LocalDateTime.now());
        Instant inst = Instant.now();
        tradeLog.setUtilTime(Date.from(inst));
        return tradeLogRepository.save(tradeLog);
    }

}
