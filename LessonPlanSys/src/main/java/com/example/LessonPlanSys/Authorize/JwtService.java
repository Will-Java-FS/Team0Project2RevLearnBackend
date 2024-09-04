package com.example.LessonPlanSys.Authorize;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {
    public static final String SECRET = "A1B2C3D4E5F6G7H8I9J0K1L2M3N4O5P6Q7R8S9T0U1V2W3X4Y5Z6A7B8C9D0E1F2G3H4I5J6K7L8M9N0O1P2Q3R4S5T6U7V8W9X0Y1Z2A3B4C5D6E7F8G9H0I1J2K3L4M5N6O7P8Q9R0S1T2U3V4W5X6Y7Z8A9B0C1D2E3F4G5H6I7J8K9L0M1N2O3P4Q5R6S7T8U9V0W1X2Y3Z4A5B6C7D8E9F0G1H2I3J4K5L6M7N8O9P0Q1R2S3T4U5V6";
    private static final long validity = TimeUnit.MINUTES.toMillis(30);

    private SecretKey generateKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));
    }
    
    public String generateToken(UserDetails userDetails) {
        Map<String, String> claims = new HashMap<>();
        claims.put("this is a ?", "test");

        return Jwts.builder()
            .claims(claims)
            .subject(userDetails.getUsername())
            .issuedAt(Date.from(Instant.now()))
            .expiration(Date.from(Instant.now().plusMillis(validity)))
            .signWith(generateKey())
            .compact();
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
            .verifyWith(generateKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
            .verifyWith(generateKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public boolean isTokenValid(String token){
        return getClaims(token)
            .getExpiration()
            .after(Date.from(Instant.now()));
    }
}
