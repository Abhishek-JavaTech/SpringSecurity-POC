package com.example.SpringSecurity_CustomCSRF.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.stereotype.Service;

@Service
public class CustomCSRFHandler extends CsrfTokenRequestAttributeHandler {

    @Override
    public String resolveCsrfTokenValue(HttpServletRequest request, CsrfToken csrfToken) {
        return super.resolveCsrfTokenValue(request, csrfToken);
    }
}
