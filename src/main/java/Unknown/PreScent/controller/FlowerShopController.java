package Unknown.PreScent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Unknown.PreScent.service.FlowerShopService;
import Unknown.PreScent.entity.FlowerShopEntity;

import java.util.Optional;

@RestController
@RequestMapping("/flower-shops")
public class FlowerShopController {

    private final FlowerShopService flowerShopService;

    @Autowired
    public FlowerShopController(FlowerShopService flowerShopService)
    {
        this.flowerShopService = flowerShopService;
    }

    @PostMapping("/add")
    public FlowerShopEntity addFlowerShop(@RequestBody Integer shopKey, Integer sellerKey, String shopName, String shopPhoneNum, String shopLocation, int[][] openingHours, boolean isOpened, String[] holiday)
    {
        return flowerShopService.addFlowerShop(sellerKey, shopName, shopPhoneNum, shopLocation, openingHours, isOpened, holiday);
    }

    @GetMapping("/{shopKey}")
    public Optional<FlowerShopEntity> getFlowerShopBySellerKey(@PathVariable Integer shopKey)
    {
        return flowerShopService.getFlowerShopByshopKey(shopKey);
    }
}
