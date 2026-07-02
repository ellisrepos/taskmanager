package org.ellis.taskmanager.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.ellis.taskmanager.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
/*
 Authentication
 - Email + Password -> Identity Verified
 Authorization
 - JWT -> Allowed to access protected APIs
 */
@Service
public class JwtServiceImpl implements JwtService{
    @Value("${jwt.secret}")
    private String secret;

    /*
    Generates a signed JWT for an authenticated user.
    The user's email is stored as the token's subject.
    The JWT is signed with the server's signing key so it can
    be verified on future requests.
    */
    @Override
    public String generateToken(User user) {
        return Jwts.builder().subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    @Override
    public boolean isTokenValid(String token, User user) {
        String email = extractEmail(token);
        return (email.equals(user.getEmail()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token)  {
        // Use parser(), set signing key, then build() to get a JwtParser for parsing.
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /*
    Convert the JWT secret into a cryptographic signing key.
    Signing is not encryption. It proves the token was created by our server
    and was not modified. Server signs secret with HMAC-SHA256
    It answers how do I know this JWT really came from my server?
    Without a signature, our server can't tell JWT content's modified
    JWT + Secret + HMAC-SHA256 = Digital Signature
    Final JWT = Header.Payload.Signature
    Signature is the HMAC Signature
    HMAC signing = proves the data is authentic and unchanged.
                    JWT
                     │
                     ▼
           Header.Payload.Signature
                     │
                     ▼
             Compute HMAC again
                     │
                     ▼
             Compare signatures
  1. Uses your secret signing key.
  2. Computes a new HMAC-SHA256 signature from the received header and payload.
  3. Compares it with the signature that is inside JWT.
    */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
