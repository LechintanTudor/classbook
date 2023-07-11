package xyz.lechi.classbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.lechi.classbook.model.JwtToken;

import java.util.Optional;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    Optional<JwtToken> findByToken(String token);
}
