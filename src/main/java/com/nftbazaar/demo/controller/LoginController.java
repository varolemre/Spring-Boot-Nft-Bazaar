package com.nftbazaar.demo.controller;

import com.nftbazaar.demo.entity.Role;
import com.nftbazaar.demo.entity.User;
import com.nftbazaar.demo.request.LoginRequest;
import com.nftbazaar.demo.request.LoginResponse;
import com.nftbazaar.demo.security.JwtTokenProvider;
import com.nftbazaar.demo.security.MyUserDetailsService;
import com.nftbazaar.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;
    private final MyUserDetailsService myUserDetailsService;



    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody  LoginRequest loginRequest){
        Optional<User> byMail = userService.findByMail(loginRequest.getUsername());
        System.out.println(byMail);
        System.out.println(byMail);
        if(byMail.isPresent()){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        }
        return ResponseEntity.ok(loginResponse(loginRequest.getUsername()));
    }

    public LoginResponse loginResponse(String mail){
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(mail);
        String jwt = jwtTokenProvider.createToken(mail, new Role("ROLE_USER"));
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMail(mail);
        loginResponse.setCreatedDate(new Date());
        loginResponse.setJwt(jwt);
        loginResponse.setExpiredDate(new Date(new Date().getTime() + JwtTokenProvider.validityInMilliseconds));
        return loginResponse;
    }

}
