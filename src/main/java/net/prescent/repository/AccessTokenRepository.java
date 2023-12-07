package net.prescent.repository;

import net.prescent.entity.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;



public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {
    Optional<AccessToken> findByToken(String token);
    void deleteByToken(String token);
}