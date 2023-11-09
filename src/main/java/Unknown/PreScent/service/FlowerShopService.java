package Unknown.PreScent.service;

import Unknown.PreScent.dto.FlowerShopDto;
import Unknown.PreScent.entity.FlowerShopEntity;
import Unknown.PreScent.repository.FlowerShopRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class FlowerShopService {
    private final FlowerShopRepository flowerShopRepo;

    public FlowerShopService(FlowerShopRepository flowerShopRepo)
    {
        this.flowerShopRepo = flowerShopRepo;
    }


    public FlowerShopEntity addFlowerShop(Integer sellerKey, String shopName, String shopPhoneNum, String shopLocation, int[][] openingHours, boolean isOpened, String[] holiday)
    {
        FlowerShopEntity flowerShopEntity = new FlowerShopEntity(sellerKey, shopName, shopPhoneNum, shopLocation, openingHours, isOpened, holiday);
        return flowerShopRepo.save(flowerShopEntity);
    }

    public Optional<FlowerShopEntity> getFlowerShopByshopKey(Integer shopKey)
    {
        return flowerShopRepo.findByshopKey(shopKey);
    }

    public Optional<FlowerShopEntity> getFlowerShopBySellerKey(Integer sellerKey)
    {
        return flowerShopRepo.findBySellerKey(sellerKey);
    }
}
