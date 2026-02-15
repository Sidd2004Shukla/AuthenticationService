package org.example.uberprojectauthservice.service;

import org.example.uberprojectauthservice.helpers.AuthPassengerDetails;
import org.example.uberprojectauthservice.models.Passenger;
import org.example.uberprojectauthservice.repository.passengerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * this class is responsible for loading the user in userdetails object for auth
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final passengerRepository passengerRepository;
    public UserDetailsServiceImpl(passengerRepository passengerRepository, passengerRepository passengerRepository1) {
        this.passengerRepository = passengerRepository1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Passenger> passenger=passengerRepository.findPassengerByEmail(username);
        if(passenger.isPresent()){
            return new AuthPassengerDetails(passenger.get());
        }
        else{
            throw new UsernameNotFoundException("this passenger does not exist");
        }
    }
}
