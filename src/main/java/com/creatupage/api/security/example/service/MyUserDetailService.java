package com.creatupage.api.security.example.service;

import com.creatupage.api.security.example.model.MyUserDetails;
import com.creatupage.api.security.example.model.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    //private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)  {
        /*User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username does not exist"));*/
        return MyUserDetails.builder().username("Cristian").build();
    }
}
