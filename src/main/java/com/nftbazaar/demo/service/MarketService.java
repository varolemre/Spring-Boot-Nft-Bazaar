package com.nftbazaar.demo.service;

import com.nftbazaar.demo.entity.Market;
import com.nftbazaar.demo.entity.Nft;
import com.nftbazaar.demo.entity.User;
import com.nftbazaar.demo.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final MarketRepository marketRepository;
    private final UserService userService;
    private final NftService nftService;
    private final TradeLogService tradeLogService;

    public String buyNft(Long userId, Long nftId){
        Long currentUserBalance= userService.getUserBalance(userId);
        Long nftCost = nftService.getNftCost(nftId);
        Nft currentNft = nftService.getNftById(nftId);
        User nftsUser = currentNft.getUser();
        User currentUser = userService.getUserById(userId);
        Long nftUserId = currentNft.getUser().getId();
        if(nftUserId == userId){
            tradeLogService.saveTradeLog(currentUser,currentNft,"Hata E505");
            return "Hata, bu nft'ye sahipsiniz!";
        }
        if(currentNft.isSellStatus()==false){
            tradeLogService.saveTradeLog(currentUser,currentNft,"Hata E506");
            return "Bu Nft Satılık Değil!";
        }

        if(currentUserBalance>nftCost){
            currentUser.setBalance(currentUserBalance-nftCost);
            currentNft.setUser(currentUser);
            Long sellerUserBalance = nftsUser.getBalance();
            sellerUserBalance=sellerUserBalance+nftCost;
            nftsUser.setBalance(sellerUserBalance);
            Market marketByNftId = marketRepository.findByNftId(nftId);
            marketRepository.delete(marketByNftId);
            currentNft.setSellStatus(false);
            userService.saveUser(currentUser);
            userService.saveUser(nftsUser);
            nftService.saveNft(currentNft);
            tradeLogService.saveTradeLog(currentUser,currentNft,"Başarılı");
            return "başarılı";
        }else if(nftCost>currentUserBalance){
            tradeLogService.saveTradeLog(currentUser,currentNft,"Hata E606");
            return "başarısız bakiye yetersiz";
        }
        tradeLogService.saveTradeLog(currentUser,currentNft,"Hata E100");
        return "başarısız bilinmeyen haya";
    }

    public String sellNft(Long userId, Long nftId, Long amount){
        //Satmak isteyen User
        User currentUser = userService.getUserById(userId);
        //Satılmak istenen Nft
        Optional<Nft> currentNft = nftService.findById(nftId);
        //Satılmak İstenen Nft'ye sahip user'ın Id'si
        Long idWhichSellNftUserId = currentNft.get().getUser().getId();
        System.out.println(currentNft.get().getId());
        System.out.println(idWhichSellNftUserId);

        if(userId==idWhichSellNftUserId){
            Market market = new Market();
            currentNft.get().setSellStatus(true);
            currentNft.get().setCost(amount);
            nftService.saveNft(currentNft.get());
            market.setUser(currentUser);
            market.setNft(currentNft.get());
            market.setAmount(amount);
            marketRepository.save(market);
            return "Başarılı";
        }
        return "Bilinmeyen Hata";
    }

}
