package br.com.apiserver.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class JwtService {

    private final String secret;
    private final String issuer;
    private final Long expirationTime;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.issuer}") String issuer,
            @Value("${jwt.expiration-timer}") Long expirationTime) {
        this.secret = secret;
        this.issuer = issuer;
        this.expirationTime = expirationTime;
    }

    public String generateToken(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(username)
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(expirationTime())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error generating token", e);
        }
    }

    public Optional<String> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secret);

            String subject = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();

            return Optional.of(subject);
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }

    private Instant expirationTime() {
        return Instant.now().plusMillis(expirationTime);
    }

}