package com.kzvo.ptmbackend.config.security.jwt;

import com.kzvo.ptmbackend.exceptions.jwt.JwtAuthenticationException;
import com.kzvo.ptmbackend.persistence.entity.user.RoleEntity;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validTime;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String username, String password, List<RoleEntity> roles) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("password", password);
        claims.put("roles", getRolesNames(roles));
        Date validity = new Date(new Date().getTime() + validTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(getEmail(token), getPassword(token)));
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String getPassword(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("password", String.class);
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

            return claims.getBody().getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException | NullPointerException e) {
            throw new JwtAuthenticationException("JWT token is invalid");
        }
    }

    private List<String> getRolesNames(List<RoleEntity> roles) {
        return roles.stream().map(RoleEntity::getName).collect(Collectors.toList());
    }

}
