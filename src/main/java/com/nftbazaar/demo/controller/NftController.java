package com.nftbazaar.demo.controller;

import com.nftbazaar.demo.entity.Nft;
import com.nftbazaar.demo.service.NftService;
import com.nftbazaar.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nft")
@CrossOrigin
@RequiredArgsConstructor
public class NftController {

    private final NftService nftService;
    private final UserService userService;

    @GetMapping
    public List<Nft> getAllNfts(){
        return nftService.getAllNfts();
    }

    @GetMapping("/{id}")
    public Nft getNftById(@PathVariable Long id){
        return nftService.getNftById(id);
    }

    @GetMapping("/{nftId}/cost")
    public Long getNftCost(@PathVariable Long nftId){
        return nftService.getNftCost(nftId);
    }

    @GetMapping("/user/{userId}")
    public List<Nft> getNftByUserId(@PathVariable Long userId){
        return nftService.getNftByUserId(userId);
    }

    @PostMapping("/{userId}/{nftId}")
    public String buyNft(@PathVariable Long userId, @PathVariable Long nftId){
    return nftService.buyNft(userId,nftId);
    }

    @GetMapping("/onsale")
    public List<Nft> getNftOnSale(){
        return nftService.getSellerNftByStatusId();
    }

    @PostMapping("/{userId}/{nftId}/{amount}")
    public String sellNft(@PathVariable Long userId, @PathVariable Long nftId, @PathVariable Long amount){
        return nftService.sellNft(userId,nftId,amount);
    }


}
