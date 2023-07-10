package xyz.lechi.classbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.lechi.classbook.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
