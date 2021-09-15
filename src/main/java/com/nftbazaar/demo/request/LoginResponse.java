package com.nftbazaar.demo.request;

import lombok.Data;

import java.util.Date;

@Data
public class LoginResponse {

    private String jwt;
    private Date expiredDate;
    private Date createdDate;
    private String mail;
}
