package org.ellis.taskmanager.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.ellis.taskmanager.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService{
    @Value("${jwt.secret}")
    private String secret;

    /*
    Generate JWT for authenticate user who logins with a valid
    email and password that user registered with.
    Email's stored as the subject. Token is signed so server
    can verity it later.
     */
    @Override
    public String generateToken(User user) {
        return Jwts.builder().subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(getSigningKey())
                .compact();
    }
    /*
    Convert the JWT secret into a cryptographic signing key.
    Signing is not encryption. It proves the token was created by our server
    and was not modified. Server signs secret with HMAC-SHA256
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
