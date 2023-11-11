package Unknown.PreScent.service;

import Unknown.PreScent.entity.FlowerShopEntity;
import Unknown.PreScent.entity.SellerEntity;
import Unknown.PreScent.repository.FlowerShopRepository;
import Unknown.PreScent.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlowerShopService {
    private final FlowerShopRepository flowerShopRepo;
    private final SellerRepository sellerRepo;

    public FlowerShopService(FlowerShopRepository flowerShopRepo, SellerRepository sellerRepo)
    {
        this.flowerShopRepo = flowerShopRepo;
        this.sellerRepo = sellerRepo;
    }


    public FlowerShopEntity addFlowerShop(Integer sellerKey, String shopName, String shopPhoneNum, String shopLocation, int[][] openingHours, boolean isOpened, String[] holiday)
    {
        FlowerShopEntity flowerShopEntity = new FlowerShopEntity(shopName, shopPhoneNum, shopLocation, openingHours, isOpened, holiday);
        Optional<SellerEntity> sellerEntity = sellerRepo.findBySellerKey(sellerKey);
        if(sellerEntity.isPresent()){
            SellerEntity foundSellerEntity = sellerEntity.get();
            flowerShopEntity.setSellerEntity(foundSellerEntity);
            return flowerShopRepo.save(flowerShopEntity);
        }
        else{
            // 일치하는 seller없음. 시스템 오류 seller가 서버에 저장되어 있지 않음
            System.out.print("-----------------------------------------sellerEntity를 sellerKey로 찾았는데 없단다 -----------------------------------------");
        }
        return flowerShopRepo.save(flowerShopEntity);
    }

    public Optional<FlowerShopEntity> getFlowerShopByshopKey(Integer shopKey)
    {
        return flowerShopRepo.findByshopKey(shopKey);
    }

}
