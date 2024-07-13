package com.example.SpringSecurity_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            // for Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
    @Bean
    public UserDetailsService userDetails(){
        var userDetailsService = new InMemoryUserDetailsManager();

        var userDetails = User.withUsername("abhishek")
                                        .password("rangari")
                                        .authorities("read", "write").build();
        userDetailsService.createUser(userDetails);
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic(
                (cust) -> cust.realmName("ss-part1")
        );

        httpSecurity.authorizeHttpRequests(
                (requestMatcherRegistry) -> requestMatcherRegistry.requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated()
        );

        return httpSecurity.build();
    }


}
