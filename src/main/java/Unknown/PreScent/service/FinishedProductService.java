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
        FinishedProductEntity finishedProductEntity = new FinishedProductEntity(shopKey, fpName, fpTag, fpImage, fpPrice, fpState, fpFlowerList);
        return finishedProductRepo.save(finishedProductEntity);
    }
/*
    private void validateDuplicatedFp(FinishedProductEntity fpEntity) {
        finishedProductRepo.(fpEntity.getFpName())
                .ifPresent(s ->{
                    throw new IllegalStateException("이미 등록된 사업자입니다.");
                });
        finishedProductRepo.findBySellerId(seller.getSellerId())
                .ifPresent(s ->{
                    throw new IllegalStateException("이미 사용중인 아이디입니다.");
                });
    }

 */

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
//    public Optional<List<FinishedProduct>> getFinishedProductWithFlower(String flower)
//    {
//        return finishedProductRepo.findByValue(flower);
//    }
}