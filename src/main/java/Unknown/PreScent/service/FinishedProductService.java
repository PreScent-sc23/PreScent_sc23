package Unknown.PreScent.service;

import Unknown.PreScent.dto.FinishedProductDto;
import Unknown.PreScent.entity.FinishedProductEntity;
import Unknown.PreScent.entity.FlowerShopEntity;
import Unknown.PreScent.repository.FinishedProductRepository;
import Unknown.PreScent.repository.FlowerShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
public class FinishedProductService {

    final FinishedProductRepository finishedProductRepo;
    final FlowerShopRepository flowerShopRepo;

    public FinishedProductService(FinishedProductRepository finishedProductRepo, FlowerShopRepository flowerShopRepo) {
        this.finishedProductRepo = finishedProductRepo;
        this.flowerShopRepo = flowerShopRepo;
    }


    public FinishedProductEntity addFinishedProduct(Integer shopKey, FinishedProductDto finishedProductDto){
        validateDuplicatedFp(shopKey, finishedProductDto.getFpName());
        // FinishedProductEntity finishedProductEntity = FinishedProductEntity.finishedProductDtotoEntity(finishedProductDto);
        return addFinishedProductToShop(shopKey, finishedProductDto);
    }

    // 테스트용
    public FinishedProductEntity addFinishedProduct(Integer shopKey, String fpName, String fpTag, MultipartFile fpImage, Integer fpPrice, String fpDetail, String[] fpFlowerList){
        validateDuplicatedFp(shopKey, fpName);

        FinishedProductDto finishedProductDto = new FinishedProductDto(fpImage, fpName, fpTag, fpPrice, fpDetail, fpFlowerList);
        FinishedProductEntity addedFinishedProductEntity = addFinishedProductToShop(shopKey, finishedProductDto);

        return finishedProductRepo.save(addedFinishedProductEntity);
    }


    private FinishedProductEntity addFinishedProductToShop(Integer shopKey, FinishedProductDto finishedProductDto) {
        FinishedProductEntity finishedProductEntity = FinishedProductEntity.finishedProductDtotoEntity(finishedProductDto);
        Optional<FlowerShopEntity> foundFlowerShopEntity =  flowerShopRepo.findByshopKey(shopKey);
        if(foundFlowerShopEntity.isPresent()){
            System.out.println("---------------------------------in addFinishedProductToShop foundFlowerShopEntity.get()전");
            FlowerShopEntity flowerShopEntity = foundFlowerShopEntity.get();
            System.out.println("---------------------------------in addFinishedProductToShop foundFlowerShopEntity.get()후"+foundFlowerShopEntity.get().getShopKey());
            finishedProductEntity.setFlowerShopEntity(flowerShopEntity);
            System.out.println("---------------------------------in addFinishedProductToShop setFlowershopEntity후"+finishedProductEntity);
            finishedProductRepo.save(finishedProductEntity);
            flowerShopRepo.save(flowerShopEntity);
            System.out.println("---------------------------------in addFinishedProductToShop save끝");
            return finishedProductEntity;
        }
        else
        {
            throw new IllegalStateException("인식할 수 없는 매장입니다. 완제품을 등록할 수 없습니다.");
        }
    }

    private void validateDuplicatedFp(Integer shopKey, String fpName) {
        Optional<FlowerShopEntity> foundFlowerShopEntity = flowerShopRepo.findByshopKey(shopKey);
        if (!foundFlowerShopEntity.isPresent()) {
            throw new IllegalStateException("인식할 수 없는 매장입니다. 완제품을 등록할 수 없습니다.");
        }
        FlowerShopEntity flowerShopEntity = foundFlowerShopEntity.get();
        if (flowerShopEntity.getFinishedProductEntityList() == null) {
            System.out.println("매장이 가진 완제품이 존재하지 않습니다.");
            return;
        }
        List<FinishedProductEntity> finishedProductEntityList = flowerShopEntity.getFinishedProductEntityList();
        for (FinishedProductEntity fpEntity : finishedProductEntityList) {
            if (fpEntity.getFpName().equals(fpName)) {
                throw new IllegalStateException(fpName + "는 이미 등록된 상품입니다.");
            }
        }
    }
    public Optional<FinishedProductEntity> getFinishedProductWithFpKey(Integer fpKey){
        return finishedProductRepo.findByFpKey(fpKey);
    }
    public Optional<List<FinishedProductEntity>> getFinishedProductWithFpName(String fpName)
    {
        return finishedProductRepo.findByFpNameContaining(fpName);
    }
    public Optional<List<FinishedProductEntity>> getFinishedProductWithFpTag(String fpTag)
    {
        return finishedProductRepo.findByFpTagContaining(fpTag);
    }
//    public Optional<List<FinishedProductEntity>> getFinishedProductWithShopKey(Integer shopKey){
//        return finishedProductRepo.findByShopKey(shopKey);
//    }
//    public Optional<List<FinishedProduct>> getFinishedProductWithFlower(String flower)
//    {
//        return finishedProductRepo.findByValue(flower);
//    }
}