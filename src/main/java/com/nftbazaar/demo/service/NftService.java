package com.nftbazaar.demo.service;

import com.nftbazaar.demo.entity.Nft;
import com.nftbazaar.demo.entity.User;
import com.nftbazaar.demo.repository.NftRepository;
import com.nftbazaar.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NftService {

    private final NftRepository nftRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TradeLogService tradeLogService;

    public List<Nft> getAllNfts() {
        return nftRepository.findAll();
    }

    public Nft getNftById(Long nftId){
        return nftRepository.findById(nftId).orElse(null);
    }

    public Long getNftCost(Long nftId){
        Optional<Nft> nft = nftRepository.findById(nftId);
        return nft.get().getCost();
    }

    public Optional<Nft> findById(Long id){
        return nftRepository.findById(id);
    }

    public List<Nft> getNftByUserId(Long userId){
        return nftRepository.findByuser_Id(userId);
    }

    public List<Nft> getSellerNftByStatusId(){
        return nftRepository.findBysellStatus(true);
    }

    public Nft saveNft(Nft nft){
        return nftRepository.save(nft);
    }

    public String buyNft(Long userId, Long nftId){
        Long currentUserBalance= userService.getUserBalance(userId);
        Long nftCost = getNftCost(nftId);
        Nft currentNft = getNftById(nftId);
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
            currentNft.setSellStatus(false);
            userRepository.save(currentUser);
            userRepository.save(nftsUser);
            nftRepository.save(currentNft);
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
        Optional<Nft> currentNft = nftRepository.findById(nftId);
        //Satılmak İstenen Nft'ye sahip user'ın Id'si
        Long idWhichSellNftUserId = currentNft.get().getUser().getId();
        System.out.println(currentNft.get().getId());
        System.out.println(idWhichSellNftUserId);

        if(userId==idWhichSellNftUserId){
            currentNft.get().setSellStatus(true);
            currentNft.get().setCost(amount);
            nftRepository.save(currentNft.get());
            return "Başarılı";
        }
    return "Bilinmeyen Hata";
    }
}
