package com.example.SpringSecurity_JwtImplementation;

import com.example.SpringSecurity_JwtImplementation.models.CustomGrantedAuthority;
import com.example.SpringSecurity_JwtImplementation.models.CustomUser;
import com.example.SpringSecurity_JwtImplementation.repos.AuthorityRepository;
import com.example.SpringSecurity_JwtImplementation.repos.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class SpringSecurityJwtImplementationApplication implements CommandLineRunner {

	private PasswordEncoder passwordEncoder;
	private UsersRepository usersRepository;
	private AuthorityRepository authorityRepository;

	public SpringSecurityJwtImplementationApplication(PasswordEncoder passwordEncoder, UsersRepository usersRepository, AuthorityRepository authorityRepository) {
		this.passwordEncoder = passwordEncoder;
		this.usersRepository = usersRepository;
		this.authorityRepository = authorityRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtImplementationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var authority = new CustomGrantedAuthority("admin", "admin");
		var users = new CustomUser("admin", "admin", true);
		authority.setUsers(users);
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		users.setAuthorityList(List.of(authority));
		usersRepository.save(users);

	}
}
