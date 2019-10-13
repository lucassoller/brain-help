package com.example.demo.model.security;

import java.util.Date;
import java.util.Optional;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.expiration}")
    private int jwtExpiration;

	// cria token a partir de um usuário autenticado
    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(new Date().getTime() + jwtExpiration);

        return Jwts.builder()
            .setSubject(userPrincipal.getEmail())
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .claim("email", userPrincipal.getEmail())
            .compact();
    }

    // obtém id do usuário a partir de um token
    public Optional<String> getEmail(String jwt) {
        try {
            Claims claims = parse(jwt).getBody();
            return ofNullable(claims.getSubject());
        } catch (Exception ex) {
            return empty();
        }
    }

    private Jws<Claims> parse(String jwt) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
    }
}
