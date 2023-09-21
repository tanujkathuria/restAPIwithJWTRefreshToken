package com.developersworld.restapi.services;

import com.developersworld.restapi.entities.CustomUser;
import com.developersworld.restapi.exception.UserNotFoundException;
import com.developersworld.restapi.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUser(userRepo.findByUserName(username)
                .orElseThrow(() -> new UserNotFoundException("user is not found with this username "+username)));
    }
}
