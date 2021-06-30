package com.simple.security.jwt;

import com.simple.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private final String secret;
    private final Duration accessExpiration;
    private final Duration refreshExpiration;

    public JwtProvider(JwtProperties jwtProperties) {
        secret = jwtProperties.getSecret();
        accessExpiration = jwtProperties.getAccessExpirationDate();
        refreshExpiration = jwtProperties.getRefreshExpirationDate();
    }

    public String generateAccessToken(String username, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration.toMillis()))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String generateRefreshToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration.toMillis()))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token){
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return true;
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public List<SimpleGrantedAuthority> getRolesFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody();
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        List<String> rolesBody = (List<String>) claims.get("roles");
        if(rolesBody != null && rolesBody.size() != 0){
            roles = rolesBody.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        return roles;
    }
}
