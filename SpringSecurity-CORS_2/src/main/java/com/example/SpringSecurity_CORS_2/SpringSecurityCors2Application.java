package com.example.SpringSecurity_CORS_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringSecurityCors2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityCors2Application.class, args);
	}

	@GetMapping("/index")
//	@CrossOrigin("http://localhost")
	@CrossOrigin
	public String get(){
		return "Hello World!";
	}
}
