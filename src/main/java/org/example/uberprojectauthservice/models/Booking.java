package org.example.uberprojectauthservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","driver","passenger"})
public class Booking extends BaseModel {
    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Long Distance;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Passenger passenger;
}
