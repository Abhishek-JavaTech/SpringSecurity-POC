package com.example.SpringSecurity_CustomFilters.config;

import com.example.SpringSecurity_CustomFilters.filters.AfterAuthenticationFilter;
import com.example.SpringSecurity_CustomFilters.filters.AtAuthenticationFilter;
import com.example.SpringSecurity_CustomFilters.filters.BeforeAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            // for Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic(
                (cust) -> cust.realmName("SpringSecurity-CustomFilters")
        );

        httpSecurity.authorizeHttpRequests(
                (requestMatcherRegistry) -> requestMatcherRegistry.requestMatchers(HttpMethod.POST,"/users") .hasAnyAuthority("admin", "manager")
                .requestMatchers(HttpMethod.DELETE,"/users/**") .hasAnyAuthority("admin", "manager")
                .requestMatchers(HttpMethod.PUT,"/users/**") .hasAnyAuthority("admin", "manager")
                .requestMatchers(HttpMethod.GET, "/users").authenticated()
                .requestMatchers(AUTH_WHITELIST).permitAll()
        );
        httpSecurity.csrf((cust) -> {
           cust.disable();
        });

        httpSecurity.addFilterBefore(new BeforeAuthenticationFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AfterAuthenticationFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new AtAuthenticationFilter(), BasicAuthenticationFilter.class)
                ;

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService authenticationManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder sCryptPasswordEncoder(){
        return new SCryptPasswordEncoder(65536, 8, 1, 32, 16);
    }

}
