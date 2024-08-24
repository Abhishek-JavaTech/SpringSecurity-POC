package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.model.repositories.CustomerRepo;

@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		var customer = customerRepo.findByName(username);
		
		if(customer != null) {
			return new User(customer.getName(), customer.getPassword(), List.of(new SimpleGrantedAuthority(customer.getRole())));
		}
		
		return null;
	}

}
