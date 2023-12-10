package net.prescent.service;

import net.prescent.dto.CustomizeProductDto;
import net.prescent.entity.CustomizeProductEntity;
import net.prescent.entity.FlowerShopEntity;
import net.prescent.entity.SellerEntity;
import net.prescent.repository.CustomizeProductRepository;
import net.prescent.repository.FlowerShopRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomizeProductService {
    final private AccessTokenService accessTokenService;
    final private CustomizeProductRepository customizeProductRepo;
    final private FlowerShopRepository flowerShopRepo;
    public CustomizeProductService(AccessTokenService accessTokenService, CustomizeProductRepository customizeProductRepo, FlowerShopRepository flowerShopRepo) {
        this.accessTokenService = accessTokenService;
        this.customizeProductRepo = customizeProductRepo;
        this.flowerShopRepo = flowerShopRepo;
    }

    public CustomizeProductDto addCustomizeProduct(String token, CustomizeProductDto customizeProductDto) {
        SellerEntity sellerEntity = accessTokenService.getSellerFromToken(token);
        FlowerShopEntity flowerShopEntity = sellerEntity.getFlowerShopEntity();

        CustomizeProductEntity customizeProductEntity = new CustomizeProductEntity();
        customizeProductEntity.setAnswers(customizeProductDto.getAnswers());
        customizeProductEntity.setShopKey(flowerShopEntity.getShopKey());

        customizeProductRepo.save(customizeProductEntity);
        flowerShopEntity.setCustomizeProductKey(customizeProductEntity.getCpKey());
        flowerShopRepo.save(flowerShopEntity);
        customizeProductDto.setCpKey(customizeProductEntity.getCpKey());
        return customizeProductDto;
    }
}
