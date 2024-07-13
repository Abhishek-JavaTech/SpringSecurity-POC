package com.example.SpringSecurity_2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomAuthProvider customAuthProvider;
    private static final String[] AUTH_WHITELIST = {
            // for Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic(
                (cust) -> cust.realmName("ss-part2")
        );

        httpSecurity.authorizeHttpRequests(
                (requestMatcherRegistry) -> requestMatcherRegistry.requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated()
        ).authenticationProvider(customAuthProvider);

        return httpSecurity.build();
    }


}
