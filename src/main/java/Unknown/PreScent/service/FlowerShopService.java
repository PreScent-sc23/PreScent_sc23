package Unknown.PreScent.service;

import Unknown.PreScent.dto.FlowerShop;
import Unknown.PreScent.repository.FlowerShopRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlowerShopService {
    private final FlowerShopRepository flowerShopRepo;

    public FlowerShopService(FlowerShopRepository flowerShopRepo)
    {
        this.flowerShopRepo = flowerShopRepo;
    }


    public FlowerShop addFlowerShop(Integer sellerKey, String shopName, String shopPhoneNum, String shopLocation, int[][] openingHours, boolean isOpened, String[] holiday)
    {
        FlowerShop flowerShop = new FlowerShop(sellerKey, shopName, shopPhoneNum, shopLocation, openingHours, isOpened, holiday);
        return flowerShopRepo.save(flowerShop);
    }

    public Optional<FlowerShop> getFlowerShopByshopKey(Integer shopKey)
    {
        return flowerShopRepo.findByshopKey(shopKey);
    }
}
