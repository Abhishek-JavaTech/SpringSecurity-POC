package com.example.SpringSecurity_JwtImplementation.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    //    extractUsername
    public String getUsername() {
        return getClaimBody(token).getSubject();

    }

    public String generateToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, getExpirationTime());
    }

    public long getExpirationTime() {
        return 3600000l;
    }

    public String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignKey())
                .compact();
    }


    //    isTokenValid
    public boolean isTokenValid(UserDetails userDetails) {
        return getClaimBody(token).getSubject().equalsIgnoreCase(userDetails.getUsername());
    }


    //    isTokenExpired
    public boolean isTokenExpired() {
        return getExpiration().before(new Date());
    }

    //    extractExpiration
    public Date getExpiration() {
        return getClaimBody(token).getExpiration();
    }


    //    extractAllClaims
    private Claims getClaimBody(String token) {
        return Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token).getPayload();
    }


    //    getSignInKey
    public SecretKey getSignKey() {
        byte[] keyBytes =  "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b".getBytes();
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(keyBytes));
    }
}
