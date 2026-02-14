package org.example.uberprojectauthservice.dto;

import lombok.*;
import org.example.uberprojectauthservice.models.Passenger;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PassengerDto {
    private  String id;

    private String email;

    private String password;

    private String phoneNumber;

    private String Name;

    private String creationDate;
    public static PassengerDto from(Passenger passenger) {
        return PassengerDto.builder()
                .email(passenger.getEmail())
                .password(passenger.getPassword())
                .phoneNumber(passenger.getPhoneNumber())
                .id(passenger.getId().toString())
                .Name(passenger.getName())
                .creationDate(passenger.getCreatedAt())
                .build();


    }
}
