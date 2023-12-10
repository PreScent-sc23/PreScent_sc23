package net.prescent.service;

import lombok.extern.slf4j.Slf4j;
import net.prescent.dto.FinishedProductDto;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.entity.FlowerShopEntity;
import net.prescent.entity.SellerEntity;
import net.prescent.repository.FinishedProductRepository;
import net.prescent.repository.FlowerShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class FinishedProductService
{

    final FinishedProductRepository finishedProductRepo;
    final FlowerShopRepository flowerShopRepo;

    public FinishedProductService(FinishedProductRepository finishedProductRepo, FlowerShopRepository flowerShopRepo) {
        this.finishedProductRepo = finishedProductRepo;
        this.flowerShopRepo = flowerShopRepo;
    }


    public FinishedProductEntity addFinishedProduct(FinishedProductDto finishedProductDto) {
        validateDuplicatedFp(finishedProductDto.getShopKey(), finishedProductDto.getFpName());
        // FinishedProductEntity finishedProductEntity = FinishedProductEntity.finishedProductDtotoEntity(finishedProductDto);
        return addFinishedProductToShop(finishedProductDto);
    }


    private FinishedProductEntity addFinishedProductToShop(FinishedProductDto finishedProductDto) {
        FinishedProductEntity finishedProductEntity = FinishedProductEntity.finishedProductDtotoEntity(finishedProductDto);
        Optional<FlowerShopEntity> foundFlowerShopEntity = flowerShopRepo.findByshopKey(finishedProductDto.getShopKey());
        if (foundFlowerShopEntity.isPresent()) {
            log.debug("---------------------------------in addFinishedProductToShop foundFlowerShopEntity.get()전");
            FlowerShopEntity flowerShopEntity = foundFlowerShopEntity.get();
            log.debug("---------------------------------in addFinishedProductToShop foundFlowerShopEntity.get()후" + foundFlowerShopEntity.get().getShopKey());
            finishedProductEntity.setFlowerShopEntity(flowerShopEntity);
            log.debug("---------------------------------in addFinishedProductToShop setFlowershopEntity후" + finishedProductEntity);

            log.debug("shopKey 값 : " + finishedProductDto.getShopKey() + "--------------------------------------------");
            log.debug("fpName 값 : " + finishedProductEntity.getFpName() + "--------------------------------------------");
            log.debug("fpTag 값 : " + finishedProductEntity.getFpTag() + "--------------------------------------------");
            log.debug("fpPrice 값 : " + finishedProductEntity.getFpPrice() + "--------------------------------------------");
            log.debug("fpDetail 값 : " + finishedProductEntity.getFpDetail() + "--------------------------------------------");
            System.out.printf("fpFlowerList 값 : %s--------------------------------------------%n", (Object) finishedProductEntity.getFpFlowerList());
            if (finishedProductEntity.getFpImage() != null) {
                log.debug("여긴 addFinishedProduct fpImage를 확인" + finishedProductEntity.getFpImage() + "---------------");
            }
            finishedProductRepo.save(finishedProductEntity);
            flowerShopRepo.save(flowerShopEntity);
            log.debug("---------------------------------in addFinishedProductToShop save끝");
            return finishedProductEntity;
        }
       else {
            throw new IllegalStateException("addFinishedProduct내부 인식할 수 없는 매장입니다. 완제품을 등록할 수 없습니다.");
        }
    }

        private void validateDuplicatedFp (Integer shopKey, String fpName){
            Optional<FlowerShopEntity> foundFlowerShopEntity = flowerShopRepo.findByshopKey(shopKey);
            if (!foundFlowerShopEntity.isPresent()) {
                throw new IllegalStateException("인식할 수 없는 매장입니다. 완제품을 등록할 수 없습니다.");
            }
            FlowerShopEntity flowerShopEntity = foundFlowerShopEntity.get();
            if (flowerShopEntity.getFinishedProductEntityList() == null) {
                log.debug("매장이 가진 완제품이 존재하지 않습니다.");
                return;
            }
            Set<FinishedProductEntity> finishedProductEntityList = flowerShopEntity.getFinishedProductEntityList();
            for (FinishedProductEntity fpEntity : finishedProductEntityList) {
                if (fpEntity.getFpName().equals(fpName)) {
                    throw new IllegalStateException(fpName + "는 이미 등록된 상품입니다.");
                }
            }
        }
        public Optional<FinishedProductEntity> getFinishedProductWithFpKey (Integer fpKey){
            return finishedProductRepo.findByFpKey(fpKey);
        }
        public Optional<List<FinishedProductEntity>> getFinishedProductWithFpName (String fpName)
        {
            return finishedProductRepo.findByFpNameContaining(fpName);
        }
        public Optional<List<FinishedProductEntity>> getFinishedProductWithFpTag (String fpTag)
        {
            return finishedProductRepo.findByFpTagContaining(fpTag);
        }

    public void deleteFinishedProduct(Integer sellerKey, Integer fpKey) {

        if(sellerKey == finishedProductRepo.findByFpKey(fpKey).get().getFlowerShopEntity().getSellerEntity().getUserKey())
        {
            finishedProductRepo.deleteById(fpKey);
        }
        else
        {
            throw new IllegalStateException("삭제하려는 상품이 사용자에게 없습니다.");
        }

    }
//    public Optional<List<FinishedProductEntity>> getFinishedProductWithShopKey(Integer shopKey){
//        return finishedProductRepo.findByShopKey(shopKey);
//    }
//    public Optional<List<FinishedProduct>> getFinishedProductWithFlower(String flower)
//    {
//        return finishedProductRepo.findByValue(flower);
//    }
    }
