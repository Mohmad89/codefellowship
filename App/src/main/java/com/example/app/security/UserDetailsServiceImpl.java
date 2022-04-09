package com.example.app.security;

import com.example.app.infrastructure.CodeFellowshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CodeFellowshipRepository codeFellowshipRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(username);
        System.out.println(codeFellowshipRepository.toString());
        System.out.println("--------------------------------------------------------------------------------");
        return  codeFellowshipRepository.findByUsername(username);
    }
}
