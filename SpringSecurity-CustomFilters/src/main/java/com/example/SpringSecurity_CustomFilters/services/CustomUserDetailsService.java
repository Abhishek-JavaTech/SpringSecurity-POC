package com.example.SpringSecurity_CustomFilters.services;

import com.example.SpringSecurity_CustomFilters.model.SecurityUser;
import com.example.SpringSecurity_CustomFilters.repos.BusinessUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private BusinessUserRepository userRepository;

    public CustomUserDetailsService(BusinessUserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var businessUser = userRepository.findByUsername(username);
        if(businessUser.isPresent()){
            return new SecurityUser(businessUser.get());
        }
        throw new UsernameNotFoundException(username);
    }
}
