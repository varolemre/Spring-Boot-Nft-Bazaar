package com.nftbazaar.demo.controller;

import com.nftbazaar.demo.request.TransferRequest;
import com.nftbazaar.demo.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    public String transfer(@RequestBody TransferRequest transferRequest){
        return transferService.sendNft(transferRequest);
    }

}
