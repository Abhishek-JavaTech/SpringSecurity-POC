package com.example.SpringSecurity_CustomCSRF.repos;

import com.example.SpringSecurity_CustomCSRF.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByIdentifier(String id);
}
