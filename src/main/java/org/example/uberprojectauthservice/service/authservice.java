package org.example.uberprojectauthservice.service;

import org.example.uberprojectauthservice.repository.authrepository;
import org.springframework.stereotype.Service;

@Service
public class authservice {
    private authrepository authrepository;
    public authservice(authrepository authrepository){
        this.authrepository=authrepository;
    }
}
