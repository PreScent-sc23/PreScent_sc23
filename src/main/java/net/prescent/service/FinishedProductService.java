package net.prescent.service;

import net.prescent.entity.FinishedProductEntity;
import net.prescent.repository.FinishedProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinishedProductService {
    private final FinishedProductRepository finishedProductRepo;

    public FinishedProductService(FinishedProductRepository finishedProductRepo) {
        this.finishedProductRepo = finishedProductRepo;
    }

    public FinishedProductEntity addFinishedProduct(Integer shopKey, String fpName, String fpTag, String fpImage, Integer fpPrice, boolean fpState, String[] fpFlowerList){
        validateDuplicatedFp(shopKey, fpName, fpPrice);

        FinishedProductEntity finishedProductEntity = new FinishedProductEntity(shopKey, fpName, fpTag, fpImage, fpPrice, fpState, fpFlowerList);

        return finishedProductRepo.save(finishedProductEntity);
    }

    private void validateDuplicatedFp(Integer shopKey, String fpName, Integer fpPrice) {
        Optional<List<FinishedProductEntity>> compFPEntity = getFinishedProductWithShopKey(shopKey);
        List<FinishedProductEntity> compList = getFinishedProductWithShopKey(shopKey).get();

        Optional<List<FinishedProductEntity>> shopKeyResult = finishedProductRepo.findByShopKey(shopKey);

        Optional<List<FinishedProductEntity>> nameResult = finishedProductRepo.findByFpNameContaining(fpName);

        shopKeyResult.ifPresent(s ->{
            for(FinishedProductEntity fp : shopKeyResult.get()){
                if(fpName.equals(fp.getFpName())){
                    throw new IllegalStateException(fp.getFpName() + "는 이미 등록된 상품 이름 입니다.\n");
                    //System.out.println(fp.getFpName() + "는 이미 등록된 상품 이름 입니다.\n");
                }
            }
        });
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
    public Optional<List<FinishedProductEntity>> getFinishedProductWithShopKey(Integer shopKey){
        return finishedProductRepo.findByShopKey(shopKey);
    }
//    public Optional<List<FinishedProduct>> getFinishedProductWithFlower(String flower)
//    {
//        return finishedProductRepo.findByValue(flower);
//    }
}