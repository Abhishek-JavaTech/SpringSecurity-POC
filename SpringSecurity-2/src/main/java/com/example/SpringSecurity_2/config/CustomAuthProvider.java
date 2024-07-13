package com.example.SpringSecurity_2.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication.getName().equalsIgnoreCase("abhishek")
                && authentication.getCredentials().toString().equalsIgnoreCase("rangari")){
            return new UsernamePasswordAuthenticationToken("abhishek", "rangari", null);
        }
        throw new UsernameNotFoundException("User not exist");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
