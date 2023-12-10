package net.prescent.service;

import lombok.extern.slf4j.Slf4j;
import net.prescent.dto.FinishedProductDto;
import net.prescent.dto.FlowerShopDto;
import net.prescent.dto.LocationDto;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.entity.FlowerShopEntity;
import net.prescent.entity.SellerEntity;
import net.prescent.repository.FlowerShopRepository;
import net.prescent.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FlowerShopService {
    private final FlowerShopRepository flowerShopRepo;
    private final SellerRepository sellerRepo;
    private final AccessTokenService accessTokenService;

    public FlowerShopService(FlowerShopRepository flowerShopRepo, SellerRepository sellerRepo, AccessTokenService accessTokenService)
    {
        this.flowerShopRepo = flowerShopRepo;
        this.sellerRepo = sellerRepo;
        this.accessTokenService = accessTokenService;
    }


    public FlowerShopEntity addFlowerShop(FlowerShopDto flowerShopDto)
    {
        FlowerShopEntity flowerShopEntity = FlowerShopEntity.FlowerShopDtoToEntity(flowerShopDto);
        Optional<SellerEntity> sellerEntity = sellerRepo.findByUserKey(flowerShopDto.getUserKey());
        if(sellerEntity.isPresent()){
            SellerEntity foundSellerEntity = sellerEntity.get();
//            log.debug(foundSellerEntity.getSellerId() + foundSellerEntity.getSellerName() + foundSellerEntity.getFlowerShopEntity().getShopName()+"-----------------------------------\n\n");

            log.debug(foundSellerEntity.getIdEmail() + foundSellerEntity.getName() + "-----------------------------------\n\n");
            if(foundSellerEntity.getFlowerShopEntity() != null) {
                throw new IllegalStateException("이미 매장을 등록한 판매자입니다.");
            }
            else
            {
                flowerShopEntity.setSellerEntity(foundSellerEntity);
                flowerShopRepo.save(flowerShopEntity);
                sellerRepo.save(foundSellerEntity);
                log.debug(foundSellerEntity.getIdEmail() + foundSellerEntity.getName() + foundSellerEntity.getFlowerShopEntity().getShopName()+"-----------------------------------\n\n");
            }
            return flowerShopEntity;
        }
        else{
            throw new IllegalStateException("판매자를 조회하지 못했습니다.");
        }
    }

    public FlowerShopEntity addFlowerShop(Long businessKey, String shopName, String shopPhoneNum, String shopLocation, String description)
    {
        FlowerShopEntity flowerShopEntity = new FlowerShopEntity(shopName, shopPhoneNum, shopLocation, description);
        Optional<SellerEntity> sellerEntity = sellerRepo.findByBusinessKey(businessKey);
        if(sellerEntity.isPresent()){
            SellerEntity foundSellerEntity = sellerEntity.get();
//            log.debug(foundSellerEntity.getSellerId() + foundSellerEntity.getSellerName() + foundSellerEntity.getFlowerShopEntity().getShopName()+"-----------------------------------\n\n");

            log.debug(foundSellerEntity.getIdEmail() + foundSellerEntity.getName() + "-----------------------------------\n\n");
            if(foundSellerEntity.getFlowerShopEntity() != null) {
                throw new IllegalStateException("이미 매장을 등록한 판매자입니다.");
            }
            else
            {
                flowerShopEntity.setSellerEntity(foundSellerEntity);
                flowerShopRepo.save(flowerShopEntity);
                sellerRepo.save(foundSellerEntity);
                log.debug(foundSellerEntity.getIdEmail() + foundSellerEntity.getName() + foundSellerEntity.getFlowerShopEntity().getShopName()+"-----------------------------------\n\n");
            }
            return flowerShopEntity;
        }
        else{
            // 일치하는 seller없음. 시스템 오류 seller가 서버에 저장되어 있지 않음
            log.info("-----------------------------------------sellerEntity를 sellerKey로 찾았는데 없단다 -----------------------------------------");
        }
        return flowerShopRepo.save(flowerShopEntity);
    }

    public List<FinishedProductDto> sellerViewFPinShop(Integer userKey) {
        SellerEntity sellerEntity = sellerRepo.findByUserKey(userKey).get();
        FlowerShopEntity flowershopEntity = sellerEntity.getFlowerShopEntity();
        List<FinishedProductDto> finishedProductDtoList = new ArrayList<>();
        if(flowershopEntity.getFinishedProductEntityList() == null)
        {
            return finishedProductDtoList;
        }
        for(FinishedProductEntity finishedProductEntity : flowershopEntity.getFinishedProductEntityList())
        {
            FinishedProductDto finishedProductDto = FinishedProductDto.toFinishedProductDto(finishedProductEntity);
            finishedProductDtoList.add(finishedProductDto);
        }
        return finishedProductDtoList;
    }

    public Optional<FlowerShopEntity> getFlowerShopByshopKey(Integer shopKey)
    {
        return flowerShopRepo.findByshopKey(shopKey);
    }

    public List<FinishedProductDto> sellerViewFPinShop(String token) {
        SellerEntity sellerEntity = accessTokenService.getSellerFromToken(token);
        FlowerShopEntity flowershopEntity = sellerEntity.getFlowerShopEntity();
        List<FinishedProductDto> finishedProductDtoList = new ArrayList<>();
        if(flowershopEntity.getFinishedProductEntityList() == null)
        {
            return finishedProductDtoList;
        }
        for(FinishedProductEntity finishedProductEntity : flowershopEntity.getFinishedProductEntityList())
        {
            FinishedProductDto finishedProductDto = FinishedProductDto.toFinishedProductDto(finishedProductEntity);
            finishedProductDtoList.add(finishedProductDto);
        }
        return finishedProductDtoList;
    }

    public void setShopLocation(String token, LocationDto locationDto) {
        SellerEntity sellerEntity = accessTokenService.getSellerFromToken(token);
        FlowerShopEntity flowerShopEntity = sellerEntity.getFlowerShopEntity();

        flowerShopEntity.setLatitude(locationDto.getLatitude());
        flowerShopEntity.setLongitude(locationDto.getLongitude());
        if(locationDto.getAddress()!=null)flowerShopEntity.setAddress(locationDto.getAddress());
        flowerShopRepo.save(flowerShopEntity);
    }
}
