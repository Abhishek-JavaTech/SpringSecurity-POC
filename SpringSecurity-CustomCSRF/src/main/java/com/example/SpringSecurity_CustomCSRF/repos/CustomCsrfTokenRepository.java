package com.example.SpringSecurity_CustomCSRF.repos;

import com.example.SpringSecurity_CustomCSRF.model.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    TokenRepository tokenRepository;

    public CustomCsrfTokenRepository(TokenRepository tokenRepository){
        this.tokenRepository = tokenRepository;
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameterMap().get("X-IDENTIFIER")[0];

        Token savedToken = tokenRepository.findByIdentifier(id);
        if(savedToken != null) {
            savedToken.setToken(token.getToken());
        }else {
            Token generateToken = new Token();
            generateToken.setToken(token.getToken());
            generateToken.setIdentifier(request.getParameter("X-IDENTIFIER"));
            tokenRepository.save(generateToken);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String id = request.getParameterMap().get("X-IDENTIFIER")[0];

        Token token = tokenRepository.findByIdentifier(id);
        if(token != null)
            return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.getToken());
        return null;
    }
}
