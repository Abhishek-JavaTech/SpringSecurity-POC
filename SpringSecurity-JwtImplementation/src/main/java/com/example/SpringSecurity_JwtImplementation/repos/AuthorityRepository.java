package com.example.SpringSecurity_JwtImplementation.repos;

import com.example.SpringSecurity_JwtImplementation.models.CustomGrantedAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<CustomGrantedAuthority, Long> {
}
