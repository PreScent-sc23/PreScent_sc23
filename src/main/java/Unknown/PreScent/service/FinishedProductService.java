package Unknown.PreScent.service;

import Unknown.PreScent.entity.FinishedProductEntity;
import Unknown.PreScent.entity.FlowerShopEntity;
import Unknown.PreScent.entity.SellerEntity;
import Unknown.PreScent.repository.FinishedProductRepository;
import Unknown.PreScent.repository.FlowerShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FinishedProductService {
    @Autowired
    private FinishedProductRepository finishedProductRepo;
    @Autowired
    private FlowerShopRepository flowerShopRepo;

    public FinishedProductEntity addFinishedProduct(Integer shopKey, FinishedProductEntity finishedProductEntity){
        validateDuplicatedFp(shopKey, finishedProductEntity.getFpName());
        return addFinishedProductToShop(shopKey, finishedProductEntity);
    }

    // 테스트용
    public FinishedProductEntity addFinishedProduct(Integer shopKey, String fpName, String fpTag, String fpImage, Integer fpPrice, boolean fpState, String[] fpFlowerList){
        validateDuplicatedFp(shopKey, fpName);

        FinishedProductEntity finishedProductEntity = new FinishedProductEntity(fpName, fpTag, fpImage, fpPrice, fpState, fpFlowerList);
        finishedProductEntity = addFinishedProductToShop(shopKey,finishedProductEntity);

        return finishedProductRepo.save(finishedProductEntity);
    }

    private FinishedProductEntity addFinishedProductToShop(Integer shopKey, FinishedProductEntity finishedProductEntity) {
        Optional<FlowerShopEntity> foundFlowerShopEntity =  flowerShopRepo.findByshopKey(shopKey);
        if(foundFlowerShopEntity.isPresent()){
            FlowerShopEntity flowerShopEntity = foundFlowerShopEntity.get();
            finishedProductEntity.setFlowerShopEntity(flowerShopEntity);
            finishedProductRepo.save(finishedProductEntity);
            flowerShopRepo.save(flowerShopEntity);
            return finishedProductEntity;
        }
        else
        {
            throw new IllegalStateException("인식할 수 없는 매장입니다. 완제품을 등록할 수 없습니다.");
        }
    }

    private void validateDuplicatedFp(Integer shopKey, String fpName) {
        Optional<FlowerShopEntity> foundFlowerShopEntity =  flowerShopRepo.findByshopKey(shopKey);
        if(!foundFlowerShopEntity.isPresent()) {
            throw new IllegalStateException("인식할 수 없는 매장입니다. 완제품을 등록할 수 없습니다.");
        }
        FlowerShopEntity flowerShopEntity = foundFlowerShopEntity.get();
        List<FinishedProductEntity> finishedProductEntityList = flowerShopEntity.getFinishedProductEntityList();
        for( FinishedProductEntity fpEntity : finishedProductEntityList) {
            if(fpEntity.getFpName().equals(fpName))
            {
                throw new IllegalStateException(fpName+"는 이미 등록된 상품입니다.");
            }
        }


//        Optional<List<FinishedProductEntity>> compFPEntity = getFinishedProductWithShopKey(flower);
//        List<FinishedProductEntity> compList = getFinishedProductWithShopKey(shopKey).get();
//
//        Optional<List<FinishedProductEntity>> shopKeyResult = finishedProductRepo.findByShopKey(shopKey);
//
//        Optional<List<FinishedProductEntity>> nameResult = finishedProductRepo.findByFpNameContaining(fpName);

//        nameResult.ifPresent(s ->{
//                    //throw new IllegalStateException("이미 등록된 상품 이름 입니다.");
//
//                    for(FinishedProductEntity fp : nameResult.get()){
//                        if(shopKey.equals(fp.getShopKey())){
//                            throw new IllegalStateException(fp.getFpName() + "는 이미 등록된 상품 이름 입니다.\n");
//                            //System.out.println(fp.getFpName() + "는 이미 등록된 상품 이름 입니다.\n");
//                        }
//                    }
//                });

//        shopKeyResult.ifPresent(s ->{
//            //throw new IllegalStateException("이미 등록된 상품 이름 입니다.");
//
//            for(FinishedProductEntity fp : shopKeyResult.get()){
//                if(fpName.equals(fp.getFpName())){
//                    throw new IllegalStateException(fp.getFpName() + "는 이미 등록된 상품 이름 입니다.\n");
//                    //System.out.println(fp.getFpName() + "는 이미 등록된 상품 이름 입니다.\n");
//                }
//            }
//         });
    }



    public Optional<FinishedProductEntity> getFinishedProductWithFpKey(Integer fpKey)
    {
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