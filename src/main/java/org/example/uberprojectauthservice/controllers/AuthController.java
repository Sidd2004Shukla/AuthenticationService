package org.example.uberprojectauthservice.controllers;

import org.example.uberprojectauthservice.dto.PassengerDto;
import org.example.uberprojectauthservice.dto.PassengerSignUpRequestDto;
import org.example.uberprojectauthservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthService authservice;
    public AuthController(AuthService authservice){
        this.authservice=authservice;
    }
    @PostMapping("signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignUpRequestDto passenger){
        PassengerDto passengerDto=authservice.signupPassenger(passenger);
        return new ResponseEntity<>(passengerDto,HttpStatus.CREATED);
    }
    @GetMapping("signin/passenger")
    public ResponseEntity<?> signIn(){
        return new ResponseEntity<>(10,HttpStatus.CREATED);
    }

}
