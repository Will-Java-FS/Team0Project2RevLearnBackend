package com.example.LessonPlanSys.Authorize;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {
    public static final String SECRET = Base64.getEncoder().encodeToString("YourSecretKeyMustBeLongEnough12345678901234567890".getBytes());

    private static final long validity = TimeUnit.MINUTES.toMillis(30);

    private SecretKey generateKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));
    }
    
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("this is a ?", "test");

        return Jwts.builder()
            .addClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plusMillis(validity)))
            .signWith(generateKey())
            .compact();
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(generateKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(generateKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public boolean isTokenValid(String token){
        return getClaims(token)
            .getExpiration()
            .after(Date.from(Instant.now()));
    }
}