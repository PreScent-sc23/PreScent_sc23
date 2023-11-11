package Unknown.PreScent.service;

import Unknown.PreScent.entity.FinishedProductEntity;
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