package com.nftbazaar.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Nft nft;

    private Long amount;
}
