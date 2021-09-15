package com.nftbazaar.demo.controller;

import com.nftbazaar.demo.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController {

    private final MarketService marketService;

    @PostMapping("/buy/{userId}/{nftId}")
    public String buyNft(@PathVariable Long userId, @PathVariable Long nftId){
        return marketService.buyNft(userId,nftId);
    }

    @PostMapping("/sell/{userId}/{nftId}/{amount}")
    public String sellNft(@PathVariable Long userId, @PathVariable Long nftId, @PathVariable Long amount){
        return marketService.sellNft(userId,nftId,amount);
    }



}
