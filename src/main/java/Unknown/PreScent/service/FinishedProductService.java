package Unknown.PreScent.service;

import Unknown.PreScent.entity.FinishedProductEntity;
import Unknown.PreScent.entity.SellerEntity;
import Unknown.PreScent.repository.FinishedProductRepository;
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
        System.out.println("중복 상품 검사 시작 입니다.\n\n");
        //Optional<List<FinishedProductEntity>> compFPEntity = getFinishedProductWithShopKey(shopKey);
        //List<FinishedProductEntity> compList = getFinishedProductWithShopKey(shopKey).get();

        //finishedProductRepo.findByShopKeyContaining(shopKey);

        Optional<List<FinishedProductEntity>> nameResult = finishedProductRepo.findByFpNameContaining(fpName);

        nameResult.ifPresent(s ->{
                    //throw new IllegalStateException("이미 등록된 상품 이름 입니다.");

                    for(FinishedProductEntity fp : nameResult.get()){
                        if(shopKey.equals(fp.getShopKey())){
                            throw new IllegalStateException("이미 등록된 상품 이름 입니다.\n");
                            //System.out.println("이미 등록된 상품 이름 입니다.\n\n");
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
        return finishedProductRepo.findByShopKeyContaining(shopKey);
    }
//    public Optional<List<FinishedProduct>> getFinishedProductWithFlower(String flower)
//    {
//        return finishedProductRepo.findByValue(flower);
//    }
}