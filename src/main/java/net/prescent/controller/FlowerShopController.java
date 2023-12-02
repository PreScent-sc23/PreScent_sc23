package net.prescent.controller;

import lombok.extern.slf4j.Slf4j;
import net.prescent.dto.FlowerShopDto;
import net.prescent.entity.FlowerShopEntity;
import net.prescent.service.FlowerShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/flower-shops")
public class FlowerShopController {

    private final FlowerShopService flowerShopService;


    FlowerShopController(FlowerShopService flowerShopService)
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
    public ResponseEntity<?> addFlowerShop(@RequestBody FlowerShopDto flowerShopDto,
                                          BindingResult bindingResult)
    {

        log.debug("---------------------------------------------------------------------");
        log.debug("sellerKey: " + flowerShopDto.getBusinessKey());
        log.debug("shopName: " + flowerShopDto.getShopName());
        log.debug("shopPhoneNum: " + flowerShopDto.getShopPhoneNum());
        log.debug("shopLocation: " + flowerShopDto.getShopLocation());
        log.debug("description: " + flowerShopDto.getDescription() + "\n");
        log.debug("---------------------------------------------------------------------");
        if (bindingResult.hasErrors()) {

            log.debug("bindingResult error!!!");
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        flowerShopService.addFlowerShop(flowerShopDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
//        return flowerShopService.addFlowerShop(sellerKey, shopName, shopPhoneNum, shopLocation, description);
    }

    @GetMapping("/{shopKey}")
    public Optional<FlowerShopEntity> getFlowerShopBySellerKey(@PathVariable Integer shopKey)
    {
        return flowerShopService.getFlowerShopByshopKey(shopKey);
    }
}
