package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception {

		http.sessionManagement(conf -> conf.invalidSessionUrl("/invalidPage").maximumSessions(1)
				.maxSessionsPreventsLogin(true).expiredUrl("/expired"));
		http.authorizeHttpRequests(
				request -> request.requestMatchers("/v1**").authenticated().anyRequest().permitAll());
		http.formLogin(conf -> conf.toString());
		http.httpBasic(conf -> conf.realmName("realm"));
		return http.build();
	}

	/*
	 * @Bean public UserDetailsService detailsService(DataSource dataSource) {
	 * return new JdbcUserDetailsManager(dataSource); }
	 */

	@Bean
	public PasswordEncoder encoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
