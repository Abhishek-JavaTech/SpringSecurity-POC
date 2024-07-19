package com.example.SpringSecurity_JwtImplementation.repos;

import com.example.SpringSecurity_JwtImplementation.models.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<CustomUser, Long> {
}
