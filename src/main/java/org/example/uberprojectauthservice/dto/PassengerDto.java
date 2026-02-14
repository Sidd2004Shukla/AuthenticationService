package org.example.uberprojectauthservice.dto;

import lombok.*;

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

    private Date creationDate;
}
