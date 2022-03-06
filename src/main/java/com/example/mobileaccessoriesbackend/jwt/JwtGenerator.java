package com.example.mobileaccessoriesbackend.jwt;

import com.example.mobileaccessoriesbackend.config.APIParameters;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtGenerator {

    private final JwtConfig jwtConfig;

    @Autowired
    public JwtGenerator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public static String generateToken(String subject, String id, Collection<? extends GrantedAuthority> authorities){
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(subject)
                .claim("authorities",authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(APIParameters.JWT_EXPIRATION)))
                .signWith(Keys.hmacShaKeyFor(APIParameters.JWT_SECRET.getBytes())).claim("id",id)
                .compact();
    }
}
