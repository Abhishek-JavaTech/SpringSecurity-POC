package com.example.SpringSecurity_JwtImplementation.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInUser {

    private String username;
    private String password;
}
