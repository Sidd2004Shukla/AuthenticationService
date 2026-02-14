package org.example.uberprojectauthservice.controllers;

import org.apache.catalina.User;
import org.example.uberprojectauthservice.dto.PassengerDto;
import org.example.uberprojectauthservice.dto.PassengerSignUpRequestDto;
import org.example.uberprojectauthservice.service.authservice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private authservice authservice;
    public AuthController(authservice authservice){
        this.authservice=authservice;
    }
    @PostMapping("signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignUpRequestDto passenger){
        return null;
    }

}
