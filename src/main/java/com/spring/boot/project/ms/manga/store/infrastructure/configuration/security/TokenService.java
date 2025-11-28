package com.spring.boot.project.ms.manga.store.infrastructure.configuration.security;

import com.spring.boot.project.ms.manga.store.infrastructure.exception.TokenExpiredException;
import com.spring.boot.project.ms.manga.store.infrastructure.exception.TokenInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final String accessCreateTokenSecret;
    private final Long accessTokenValiditySeconds;
    private final SecretKey hmacKey;

    public TokenService(@Value("${jwt.access.token.secret}") String accessCreateTokenSecret,
                        @Value("${jwt.access.token.validity.seconds}") Long accessTokenValiditySeconds) {
        this.accessCreateTokenSecret = accessCreateTokenSecret;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;

        byte[] keyBytes = accessCreateTokenSecret.getBytes(StandardCharsets.UTF_8);
        this.hmacKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Long userId, String email, Collection<? extends GrantedAuthority> authorities) {
        long expirationTime = accessTokenValiditySeconds * 1000;
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(authority -> authority.replace("ROLE_", ""))
                .collect(Collectors.toList());

        return Jwts.builder()
                .subject(String.valueOf(userId))
                .issuedAt(now)
                .expiration(expirationDate)
                .claim("email", email)
                .claim("roles", roles)
                .signWith(hmacKey)
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token)
        throws TokenExpiredException, TokenInvalidException {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(hmacKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                    Collections.emptyList());
        } catch (JwtException ex) {
            if (ex.getMessage().startsWith("JWT expired")) {
                throw new TokenExpiredException();
            }
            throw new TokenInvalidException();
        }
    }
}
