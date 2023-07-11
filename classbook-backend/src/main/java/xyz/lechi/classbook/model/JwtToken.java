package xyz.lechi.classbook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class JwtToken {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private boolean isRevoked;

    @Column(nullable = false)
    private boolean isExpired;

    public void setRevoked(boolean isRevoked) {
        this.isRevoked = isRevoked;
    }
}
