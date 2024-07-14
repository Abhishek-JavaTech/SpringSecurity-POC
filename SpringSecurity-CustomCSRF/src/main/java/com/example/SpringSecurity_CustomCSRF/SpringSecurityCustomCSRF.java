package com.example.SpringSecurity_CustomCSRF;

import com.example.SpringSecurity_CustomCSRF.constants.EncryptAlgo;
import com.example.SpringSecurity_CustomCSRF.model.BusinessUser;
import com.example.SpringSecurity_CustomCSRF.model.BusinessUserAuthorities;
import com.example.SpringSecurity_CustomCSRF.repos.BusinessUserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@OpenAPIDefinition(
		info = @Info(
				description = "this application is build with Custom Filters to represent some logs," +
						" you can also access swagger-url as follow - http://localhost:8082/swagger-ui/index.html" +
						" also check readme.txt file present in this project",
				version = "V1",
				title = "Spring Boot Security",
				contact = @Contact(
						email = "rangari.javatech@gmail.com",
						name = "Abhishek Rangari"
				)
		)
)
@SpringBootApplication
public class SpringSecurityCustomCSRF implements CommandLineRunner {

	@Autowired
	private BusinessUserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityCustomCSRF.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		var businessUser = new BusinessUser();
		businessUser.setEncrAlgo(EncryptAlgo.BCRYPT);
		businessUser.setPassword("{bcrypt}" + passwordEncoder.encode("admin"));
		businessUser.setUsername("admin");
		var authority = new BusinessUserAuthorities("admin", "admin");
		businessUser.getBusinessUserAuthorities().add(authority);
		userRepository.save(businessUser);
	}
}
