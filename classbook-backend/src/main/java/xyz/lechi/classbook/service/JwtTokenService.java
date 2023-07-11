package xyz.lechi.classbook.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import xyz.lechi.classbook.repository.JwtTokenRepository;

import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtTokenService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.duration}")
    private long tokenDuration;

    private final JwtTokenRepository jwtTokenRepository;

    public String createJwtToken(UserDetails userDetails) {
        var creationDate = System.currentTimeMillis();
        var expirationDate = creationDate + tokenDuration;

        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(creationDate))
            .setExpiration(new Date(expirationDate))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean isJwtTokenValid(String token, UserDetails userDetails) {
        var now = new Date();
        var claims = extractClaims(token);

        if (!claims.getSubject().equals(userDetails.getUsername())) {
            return false;
        }

        if (claims.getExpiration().before(now)) {
            return false;
        }

        return jwtTokenRepository
            .findByToken(token)
            .filter(t -> !t.isExpired() && !t.isRevoked())
            .isPresent();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Claims extractClaims(String token) {
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
