package xyz.lechi.classbook.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import xyz.lechi.classbook.dto.AuthUserDto;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.duration}")
    private long tokenDuration;

    public String createJwt(UserDetails userDetails) {
        var creationDate = System.currentTimeMillis();
        var expirationDate = creationDate + tokenDuration;

        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(creationDate))
            .setExpiration(new Date(expirationDate))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        var now = new Date();
        var claims = extractClaims(token);

        return claims.getSubject().equals(userDetails.getUsername())
            && claims.getExpiration().after(now);
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    private Key getSigningKey() {
        var bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }
}
