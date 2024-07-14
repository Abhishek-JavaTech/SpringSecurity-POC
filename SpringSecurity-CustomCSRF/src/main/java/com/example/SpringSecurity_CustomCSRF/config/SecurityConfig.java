package com.example.SpringSecurity_CustomCSRF.config;

import com.example.SpringSecurity_CustomCSRF.filters.AfterAuthenticationFilter;
import com.example.SpringSecurity_CustomCSRF.filters.AtAuthenticationFilter;
import com.example.SpringSecurity_CustomCSRF.filters.BeforeAuthenticationFilter;
import com.example.SpringSecurity_CustomCSRF.repos.CustomCsrfTokenRepository;
import com.example.SpringSecurity_CustomCSRF.repos.TokenRepository;
import com.example.SpringSecurity_CustomCSRF.services.CustomCSRFHandler;
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

    @Autowired
    private CustomCsrfTokenRepository customCsrfTokenRepository;

    @Autowired
    private TokenRepository tokenRepository;

    private static final String[] AUTH_WHITELIST = {
            // for Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomCSRFHandler customCSRFHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(
                (requestMatcherRegistry) -> requestMatcherRegistry.anyRequest().permitAll()
//                .requestMatchers(HttpMethod.POST,"/users") .hasAnyAuthority("admin", "manager")
//                .requestMatchers(HttpMethod.DELETE,"/users/**") .hasAnyAuthority("admin", "manager")
//                .requestMatchers(HttpMethod.PUT,"/users/**") .hasAnyAuthority("admin", "manager")
//                .requestMatchers(HttpMethod.GET, "/users").permitAll()
//                .requestMatchers(AUTH_WHITELIST).permitAll()
        );
        httpSecurity.csrf((cust) -> {
            cust.csrfTokenRepository(customCsrfTokenRepository);
            cust.csrfTokenRequestHandler(customCSRFHandler);
           cust.ignoringRequestMatchers("/users/test");
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
