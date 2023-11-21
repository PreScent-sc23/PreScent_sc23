package Unknown.PreScent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import Unknown.PreScent.service.FlowerShopService;
import Unknown.PreScent.entity.FlowerShopEntity;

import javax.validation.Valid;
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

//    @PostMapping("/add")
//    public FlowerShopEntity addFlowerShop(@RequestBody Integer sellerKey, String shopName, String shopPhoneNum, String shopLocation, int[][] openingHours, boolean isOpened, String[] holiday)
//    {
//        return flowerShopService.addFlowerShop(sellerKey, shopName, shopPhoneNum, shopLocation, openingHours, isOpened, holiday);
//    }

//@RequestBody String shopName, String shopPhoneNum, String shopLocation, String description,
    @PostMapping("/add")
    public ResponseEntity<?> addFlowerShop(@Valid @RequestParam String sSellerKey,
                                           @RequestBody FlowerShopEntity flowerShopEntity,
                                          BindingResult bindingResult)
    {
        Integer sellerKey = Integer.parseInt(sSellerKey);

//        System.out.println("---------------------------------------------------------------------");
//        System.out.println("sellerKey: " + sellerKey);
//        System.out.println("shopName: " + flowerShopEntity.getShopName());
//        System.out.println("shopPhoneNum: " + flowerShopEntity.getShopPhoneNum());
//        System.out.println("shopLocation: " + flowerShopEntity.getShopLocation());
//        System.out.println("description: " + flowerShopEntity.getDescription() + "\n");
//        System.out.println("---------------------------------------------------------------------");
        if (bindingResult.hasErrors()) {

            System.out.println("bindingResult error!!!");
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        flowerShopService.addFlowerShop(sellerKey, flowerShopEntity.getShopName(), flowerShopEntity.getShopPhoneNum(), flowerShopEntity.getShopLocation(), flowerShopEntity.getDescription());

        return ResponseEntity.status(HttpStatus.CREATED).build();
//        return flowerShopService.addFlowerShop(sellerKey, shopName, shopPhoneNum, shopLocation, description);
    }

    @GetMapping("/{shopKey}")
    public Optional<FlowerShopEntity> getFlowerShopBySellerKey(@PathVariable Integer shopKey)
    {
        return flowerShopService.getFlowerShopByshopKey(shopKey);
    }
}