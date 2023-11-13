package Unknown.PreScent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Unknown.PreScent.service.FlowerShopService;
import Unknown.PreScent.entity.FlowerShopEntity;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/flower-shops")
public class FlowerShopController {

    private final FlowerShopService flowerShopService;

    @Autowired
    public FlowerShopController(FlowerShopService flowerShopService)
    {
        this.flowerShopService = flowerShopService;
    }

    @PostMapping("/add")
    public FlowerShopEntity addFlowerShop(@RequestBody Integer sellerKey, FlowerShopEntity flowerShopEntity)
    {
        return flowerShopService.addFlowerShop(sellerKey, flowerShopEntity);
    }

    @GetMapping("/{shopKey}")
    public Optional<FlowerShopEntity> getFlowerShopBySellerKey(@PathVariable Integer shopKey)
    {
        return flowerShopService.getFlowerShopByshopKey(shopKey);
    }
}
