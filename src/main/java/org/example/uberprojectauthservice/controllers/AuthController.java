package org.example.uberprojectauthservice.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.uberprojectauthservice.dto.AuthRequestDto;
import org.example.uberprojectauthservice.dto.AuthResponseDto;
import org.example.uberprojectauthservice.dto.PassengerDto;
import org.example.uberprojectauthservice.dto.PassengerSignUpRequestDto;
import org.example.uberprojectauthservice.service.AuthService;
import org.example.uberprojectauthservice.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Value("${cookieExpiry}")
    private int cookieExpiry;
    private final AuthService authservice;
    private final JwtService  jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthController(AuthService authservice, JwtService jwtService, AuthenticationManager authenticationManager){
        this.authservice=authservice;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignUpRequestDto passenger){
        PassengerDto passengerDto=authservice.signupPassenger(passenger);
        return new ResponseEntity<>(passengerDto,HttpStatus.CREATED);
    }
    @PostMapping ("signin/passenger")
    public ResponseEntity<?> signIn(@RequestBody AuthRequestDto authRequestDto, HttpServletResponse response){
        System.out.println("Request Recieved");
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(),authRequestDto.getPassword()));
        if(authentication.isAuthenticated()){
            String jwtToken =jwtService.createToken(authRequestDto.getEmail());
            ResponseCookie cookie= ResponseCookie.from("JwtToken",jwtToken)
                    .httpOnly(true)
                    .secure(false)
                    .maxAge(cookieExpiry)
                    .path("/")
                    .build();
            response.setHeader(HttpHeaders.SET_COOKIE,cookie.toString());
            return new ResponseEntity<>(AuthResponseDto.builder().success(true).build(),HttpStatus.OK);
        }
        else{
            throw new UsernameNotFoundException("Username not found");
        }

    }

}
