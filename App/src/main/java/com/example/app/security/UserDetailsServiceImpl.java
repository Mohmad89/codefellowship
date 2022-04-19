package com.example.app.security;

import com.example.app.infrastructure.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ApplicationUserRepository codeFellowshipRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(username);
        System.out.println(codeFellowshipRepository.toString());
        System.out.println("--------------------------------------------------------------------------------");
        return  codeFellowshipRepository.findByUsername(username);
    }
}
