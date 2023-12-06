package net.prescent.service;

import net.prescent.entity.AccessToken;
import net.prescent.entity.SellerEntity;
import net.prescent.entity.UserEntity;
import net.prescent.repository.AccessTokenRepository;
import net.prescent.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class AccessTokenService {

    @Autowired
    private AccessTokenRepository accessTokenRepository;
    private final AccessTokenRepository accessTokenRepo;
    private final SellerRepository sellerRepo;
    public AccessTokenService(AccessTokenRepository accessTokenRepository, AccessTokenRepository accessTokenRepo, SellerRepository sellerRepo) {
        this.accessTokenRepository = accessTokenRepository;
        this.accessTokenRepo = accessTokenRepo;
        this.sellerRepo = sellerRepo;
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

    public SellerEntity getSellerFromToken(String token) {
        UserEntity userEntity = getUserFromToken(token);
        return getSellerFromUser(userEntity);
    }

    public SellerEntity getSellerFromUser(UserEntity userEntity)
    {
        System.out.println(userEntity.getUserKey()+"userKey는 이거 ++++++++++++++++++++");
        return sellerRepo.findByUserKey(userEntity.getUserKey()).get();
    }

}
