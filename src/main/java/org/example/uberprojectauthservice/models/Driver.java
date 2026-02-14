package org.example.uberprojectauthservice.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver extends BaseModel{
    @Column(nullable = false)
    private String Name;
    @Column(nullable = false,unique = true)
    private String licenseNumber;
    @OneToMany(mappedBy = "driver")
    @Fetch(FetchMode.SUBSELECT)
    private List<Booking> bookings=new ArrayList<>();
    @Column(nullable = false)
    private String phoneNumber;
}
