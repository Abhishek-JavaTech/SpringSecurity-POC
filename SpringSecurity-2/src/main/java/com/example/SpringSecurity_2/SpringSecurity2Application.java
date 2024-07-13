package com.example.SpringSecurity_2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				description = "this application is build with custom auth provider," +
						" you can also access swagger-url as follow - http://localhost:8082/swagger-ui/index.html",
				version = "V1",
				title = "Spring Boot Security",
				contact = @Contact(
						email = "rangari.javatech@gmail.com",
						name = "Abhishek Rangari"
				)
		)
)
@SpringBootApplication
public class SpringSecurity2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity2Application.class, args);
	}

}
