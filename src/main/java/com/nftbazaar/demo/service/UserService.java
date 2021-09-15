package com.nftbazaar.demo.service;

import com.nftbazaar.demo.entity.Nft;
import com.nftbazaar.demo.entity.User;
import com.nftbazaar.demo.repository.NftRepository;
import com.nftbazaar.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final NftRepository nftRepository;


    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> findByMail(String mail){
        return userRepository.findByMail(mail);
    }

    public Long getUserBalance(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.get().getBalance();
    }

    public User getUserByWalletId(String walletId){
        return userRepository.findBywalletId(walletId);
    }

    public User getCurrentUser(){
        return userRepository.findByuserName(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    }


}
