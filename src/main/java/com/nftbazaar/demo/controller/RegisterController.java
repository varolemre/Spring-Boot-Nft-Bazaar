package com.nftbazaar.demo.controller;

import com.nftbazaar.demo.request.RegisterRequest;
import com.nftbazaar.demo.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping("/register")
    public String registerPost(@RequestBody RegisterRequest registerRequest) {
        return registerService.registerPost(registerRequest);
    }
}
