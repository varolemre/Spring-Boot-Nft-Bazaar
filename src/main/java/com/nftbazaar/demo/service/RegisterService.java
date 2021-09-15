package com.nftbazaar.demo.service;

import com.nftbazaar.demo.entity.User;
import com.nftbazaar.demo.repository.UserRepository;
import com.nftbazaar.demo.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;

    public String registerPost(RegisterRequest registerRequest) {

        // check if these variables are exists at DB
        Optional<User> checkUser = userRepository.findByuserName(registerRequest.getUsername());
        Optional<User> checkMail = userRepository.findByMail(registerRequest.getMail());

        return userCheck(registerRequest, checkUser, checkMail);
    }

    private String userCheck(RegisterRequest registerRequest, Optional<User> checkUser, Optional<User> checkMail) {
        if(registerRequest.getMail().isEmpty() || registerRequest.getUsername().isEmpty() || registerRequest.getPassword().isEmpty()){
            return "Hata! Lütfen Her Yeri doldurun";
        }

        if (!checkUser.isPresent()) {
            if (!checkMail.isPresent()) {
                //then create a new user object
                User newUser = new User();
                newUser.setMail(registerRequest.getMail());
                newUser.setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));
                newUser.setUserName(registerRequest.getUsername());
                newUser.setWalletId(UUID.randomUUID().toString());

                userRepository.save(newUser);
                return "başarılı";

            } else { // if mail exist
                return "Başarısız";
            }
        } else { // if username exist
            return "Başarısız ";
        }
    }

    public String createUserWallet(){
        Random rand = new Random();
        String userWalletId = "";
        ArrayList licenceKod = new ArrayList();
        int randomc;
        String codeCharacters= "abcdefxyztqprsv";

        String randC="";
        for (int i=0; i<32;i++){
            randomc= rand.nextInt(10);
            if (i==0 || i==1 ||  i==4 || i==8 || i==12 || i==13 || i==19 || i==25 || i==30){
                licenceKod.add(codeCharacters.charAt(randomc));
            }
            else
                licenceKod.add(randomc);
        }

        UUID.randomUUID().toString();


        int zero=0;
        for(var a : licenceKod){
            userWalletId += a;
            if(zero==4 || zero==11 || zero==29){
                userWalletId += "-";
            }
            zero++;

        }
        return userWalletId;
    }

}
