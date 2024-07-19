package com.example.SpringSecurity_JwtImplementation.controllers;

import com.example.SpringSecurity_JwtImplementation.models.SignInUser;
import com.example.SpringSecurity_JwtImplementation.repos.AuthorityRepository;
import com.example.SpringSecurity_JwtImplementation.repos.UsersRepository;
import com.example.SpringSecurity_JwtImplementation.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    private UsersRepository usersRepository;
    private AuthorityRepository authorityRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/index")
    public String get(@RequestBody SignInUser signInUser){
//        String authHeader = request.getHeader("Authorization");

//        String[] usernamePassword = new String(Base64.getUrlDecoder().decode(authHeader.substring(6))).split(":");

        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInUser.getUsername(), signInUser.getPassword()));

        var userDetails = userDetailsService.loadUserByUsername(auth.getName());

        return jwtUtil.generateToken(userDetails);
    }

    @GetMapping("/test")
    public String test(Authentication authentication){
        return "Hello World! with JWT, User: "+authentication.getName();
    }

}
