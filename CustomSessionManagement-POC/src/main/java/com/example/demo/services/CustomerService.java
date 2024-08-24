package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.repositories.CustomerRepo;

import lombok.Data;

@Service
@Data
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	
	
}
