package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Customer;
import com.example.demo.model.repositories.CustomerRepo;

@SpringBootApplication
public class JdbcUserDetailsServicePocApplication implements CommandLineRunner {

	@Autowired
	CustomerRepo customerRepo;

	public static void main(String[] args) {
		SpringApplication.run(JdbcUserDetailsServicePocApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var customer1 = new Customer();
		customer1.setEnabled(true);
		customer1.setName("user");
		customer1.setPassword("{bcrypt}$2a$12$B6DyJKEgUEYnQJ/IdXiqreUc6QV9Vd.NUuqhbHEEw6aSYG5WSkOfO");
		customer1.setRole("read");

		var customer2 = new Customer();
		customer2.setEnabled(true);
		customer2.setName("admin");
		customer2.setPassword("{bcrypt}$2a$12$05bwgDJO4na3.lw0edTAC.jlMbhk5QXX7IBy6xquj3zMfF/xd42n.");
		customer2.setRole("admin");

		customerRepo.saveAll(List.of(customer1, customer2));
	}

}
