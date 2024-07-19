package com.example.SS_PrePostAnnotations.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(cust -> cust.realmName("SS-PrePostAnnotation"));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
//        var user1 = User.builder().username("reader").password("reader").authorities("reader").build();
//
//        var user2 = User.builder().username("admin").password("admin").authorities("admin", "manager").build();
//
//        var user3 = User.builder().username("writer").password("writer").authorities("writer").build();

        var user4 = User.builder().username("reader").password("reader").roles("reader").build();

        var user5 = User.builder().username("admin").password("admin").roles("admin", "manager").build();

        var user6 = User.builder().username("writer").password("writer").roles("writer").build();

        var udsm = new InMemoryUserDetailsManager();
//        udsm.createUser(user1);
//        udsm.createUser(user2);
//        udsm.createUser(user3);

        udsm.createUser(user4);
        udsm.createUser(user5);
        udsm.createUser(user6);

        return udsm;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
