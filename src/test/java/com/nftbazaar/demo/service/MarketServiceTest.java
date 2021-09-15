package com.nftbazaar.demo.service;

import com.nftbazaar.demo.entity.Nft;
import com.nftbazaar.demo.entity.User;
import com.nftbazaar.demo.repository.MarketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MarketServiceTest {

    @MockBean
    MarketRepository marketRepository;
    @MockBean
    UserService userService;
    @MockBean
    NftService nftService;
    @MockBean
    TradeLogService tradeLogService;
    @Autowired
    MarketService marketService;



    @Test
    void buyNft() {
        Nft nft = new Nft();
        User user = new User();
        user.setId(1L);
        nft.setUser(user);
        Mockito
                .when(userService.getUserBalance(1L))
                .thenReturn(10L);
        Mockito
                .when(nftService.getNftCost(1L))
                .thenReturn(10L);
        Mockito
                .when(nftService.getNftById(1L))
                .thenReturn(nft);
        Mockito
                .when(userService.getUserById(user.getId()))
                .thenReturn(user);
        Mockito
                .when(tradeLogService.saveTradeLog(user,nft,"Hata E505"))
                .thenReturn(null);

        String s = marketService.buyNft(1L, 1L);

        assertEquals("Hata bu nft'ye sahipsiniz!",s);

    }

    @Test
    void sellNft() {
    }
}