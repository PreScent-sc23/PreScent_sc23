package Unknown.PreScent.service;

import Unknown.PreScent.dto.FinishedProduct;
import Unknown.PreScent.repository.FinishedProductRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinishedProductService {
    private final FinishedProductRepository finishedProductRepo;

    public FinishedProductService(FinishedProductRepository finishedProductRepo) {
        this.finishedProductRepo = finishedProductRepo;
    }

    public FinishedProduct addFinishedProduct(Integer shopKey,String fpName, String fpTag, String fpImage, Integer fpPrice, boolean fpState, String[] fpFlowerList){
        FinishedProduct finishedProduct = new FinishedProduct(shopKey, fpName, fpTag, fpImage, fpPrice, fpState, fpFlowerList);
        return finishedProductRepo.save(finishedProduct);
    }

    public Optional<FinishedProduct> getFinishedProductWithFpKey(Integer fpKey)
    {
        return finishedProductRepo.findByFpKey(fpKey);
    }
    public Optional<List<FinishedProduct>> getFinishedProductWithFpName(String fpName)
    {
        return finishedProductRepo.findByFpNameContaining(fpName);
    }
    public Optional<List<FinishedProduct>> getFinishedProductWithFpTag(String fpTag)
    {
        return finishedProductRepo.findByFpTagContaining(fpTag);
    }
//    public Optional<List<FinishedProduct>> getFinishedProductWithFlower(String flower)
//    {
//        return finishedProductRepo.findByValue(flower);
//    }
}