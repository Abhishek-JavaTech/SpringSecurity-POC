package com.example.demo.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long>{

	Customer findByName(String username);

}
