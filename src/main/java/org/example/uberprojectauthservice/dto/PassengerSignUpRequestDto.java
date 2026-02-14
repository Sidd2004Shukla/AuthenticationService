package org.example.uberprojectauthservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerSignUpRequestDto {
    String email;

    String password;

    String phoneNumber;

    String Name;
}
