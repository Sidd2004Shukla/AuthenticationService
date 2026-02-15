package org.example.uberprojectauthservice.repository;

import jakarta.persistence.Entity;
import org.example.uberprojectauthservice.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface passengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findPassengerByEmail(String email);
}

