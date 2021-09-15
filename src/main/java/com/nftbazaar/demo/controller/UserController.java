package com.nftbazaar.demo.controller;

import com.nftbazaar.demo.entity.User;
import com.nftbazaar.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/{userId}/balance")
    public Long getUserBalance(@PathVariable Long userId){
        return userService.getUserBalance(userId);
    }

    @GetMapping("/w5/{walletId}")
    public User getUserByWalletId(@PathVariable String  walletId){return userService.getUserByWalletId(walletId);}

}
