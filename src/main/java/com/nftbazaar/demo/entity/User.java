package com.nftbazaar.demo.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="user")
@Data
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mail;

    private String userName;

    private String password;

    private Long balance;

    private String walletId;

    @Column(name = "ACTIVE", length = 255)
    private boolean active=true;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
