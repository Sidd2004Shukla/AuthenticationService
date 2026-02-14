package org.example.uberprojectauthservice.service;

import org.example.uberprojectauthservice.dto.PassengerDto;
import org.example.uberprojectauthservice.dto.PassengerSignUpRequestDto;
import org.example.uberprojectauthservice.models.Passenger;
import org.example.uberprojectauthservice.repository.AuthRepository;
import org.example.uberprojectauthservice.repository.passengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private passengerRepository passengerRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    AuthService(passengerRepository passengerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.passengerRepository = passengerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public PassengerDto signupPassenger(PassengerSignUpRequestDto passengerSignUpRequestDto) {
        Passenger passenger = Passenger.builder()
                .email(passengerSignUpRequestDto.getEmail())
                .name(passengerSignUpRequestDto.getName())
                .phoneNumber(passengerSignUpRequestDto.getPhoneNumber())
                .password(bCryptPasswordEncoder.encode(passengerSignUpRequestDto.getPassword()))
                .build();
        Passenger newpassenger = passengerRepository.save(passenger);
        return PassengerDto.from(newpassenger);

    }
}
