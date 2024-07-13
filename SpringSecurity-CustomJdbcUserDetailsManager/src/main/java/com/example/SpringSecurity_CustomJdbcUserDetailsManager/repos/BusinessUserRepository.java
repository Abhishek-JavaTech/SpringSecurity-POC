package com.example.SpringSecurity_CustomJdbcUserDetailsManager.repos;

import com.example.SpringSecurity_CustomJdbcUserDetailsManager.model.BusinessUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessUserRepository extends JpaRepository<BusinessUser, Long> {
    Optional<BusinessUser> findByUsername(String username);
}
