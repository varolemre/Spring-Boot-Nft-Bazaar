package com.nftbazaar.demo.request;

import lombok.Data;

@Data
public class TransferRequest {
    Long userId;
    Long amount;
    String walletId;

}
