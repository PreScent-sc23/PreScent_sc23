package net.prescent.service;

import net.prescent.entity.AccessToken;
import net.prescent.entity.UserEntity;
import net.prescent.repository.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class AccessTokenService {

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    public AccessTokenService(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }

    public String createAccessToken(UserEntity user) {
        String token = UUID.randomUUID().toString();
        AccessToken accessToken = new AccessToken(token, user);
        accessTokenRepository.save(accessToken);
        return token;
    }

    public void deleteAccessToken(String token) {
        accessTokenRepository.deleteByToken(token);
    }

    public boolean validateAccessToken(String token) {
        return accessTokenRepository.findByToken(token).isPresent();
    }

    public UserEntity getUserFromToken(String token) {
        return accessTokenRepository.findByToken(token)
                .map(AccessToken::getUser)
                .orElse(null);
    }
}
