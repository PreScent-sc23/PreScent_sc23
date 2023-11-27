package net.prescent.service;

import lombok.RequiredArgsConstructor;
import net.prescent.entity.AccessToken;
import net.prescent.dto.SellerDto;
import net.prescent.entity.SellerEntity;
import net.prescent.repository.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class AccessTokenService {

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    public AccessTokenService(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }

    public String createAccessToken(SellerEntity seller) {
        String token = UUID.randomUUID().toString();
        AccessToken accessToken = new AccessToken(token, seller);
        accessTokenRepository.save(accessToken);
        return token;
    }

    public void deleteAccessToken(String token) {
        accessTokenRepository.deleteByToken(token);
    }

    public boolean validateAccessToken(String token) {
        return accessTokenRepository.findByToken(token).isPresent();
    }

    public SellerEntity getSellerFromToken(String token) {
        return accessTokenRepository.findByToken(token)
                .map(AccessToken::getSeller)
                .orElse(null);
    }
}
