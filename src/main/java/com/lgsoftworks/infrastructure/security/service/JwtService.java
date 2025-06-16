package com.lgsoftworks.infrastructure.security.service;

import com.lgsoftworks.infrastructure.security.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final JwtProperties jwtProperties;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails, String role) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", role);
        return generateToken(extraClaims, userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (ExpiredJwtException e) {
            return e.getClaims();
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid JWT token or mal formed", e);
        }
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean canTokenBeRenewed(String token) {
        try {
            Claims claims = extractAllClaims(token);
            Date expiration = claims.getExpiration();
            long currentTime = System.currentTimeMillis();
            return expiration.before(new Date(currentTime)) &&
                    expiration.getTime() + jwtProperties.getRefreshWindow() > currentTime;
        } catch (Exception e) {
            return false;
        }
    }

    public String renewToken(String token, UserDetails userDetails) {
        if (!canTokenBeRenewed(token)) {
            throw new IllegalArgumentException("The JWT couldn't be renewed");
        }

        Claims claims = extractAllClaims(token);  // extraemos todos los claims originales
        Map<String, Object> extraClaims = new HashMap<>();
        if (claims.get("role") != null) {
            extraClaims.put("role", claims.get("role"));
        }
        return generateToken(extraClaims, userDetails);
    }

}
