package net.prescent.controller;

import lombok.extern.slf4j.Slf4j;
import net.prescent.dto.FinishedProductDto;
import net.prescent.dto.FlowerShopDto;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.entity.FlowerShopEntity;
import net.prescent.entity.SellerEntity;
import net.prescent.entity.UserEntity;
import net.prescent.service.AccessTokenService;
import net.prescent.service.FlowerShopService;
import net.prescent.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/flower-shops")
public class FlowerShopController {

    private final FlowerShopService flowerShopService;
    private final AccessTokenService accessTokenService;
    private final UserService userService;


    FlowerShopController(FlowerShopService flowerShopService, AccessTokenService accessTokenService, UserService userService)
    {
        this.flowerShopService = flowerShopService;
        this.accessTokenService = accessTokenService;
        this.userService = userService;
    }

//    @PostMapping("/add")
//    public FlowerShopEntity addFlowerShop(@RequestBody Integer sellerKey, String shopName, String shopPhoneNum, String shopLocation, int[][] openingHours, boolean isOpened, String[] holiday)
//    {
//        return flowerShopService.addFlowerShop(sellerKey, shopName, shopPhoneNum, shopLocation, openingHours, isOpened, holiday);
//    }

//@RequestBody String shopName, String shopPhoneNum, String shopLocation, String description,
    @PostMapping("/add")
    public ResponseEntity<?> addFlowerShop(@RequestHeader String Authorization,@RequestBody FlowerShopDto flowerShopDto,
                                          BindingResult bindingResult)
    {

        String token = Authorization.substring(7);
        flowerShopDto.setUserKey(accessTokenService.getUserFromToken(token).getUserKey());

        System.out.println(("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+token));
        UserEntity userEntity = accessTokenService.getUserFromToken(token);
        flowerShopDto.setUserKey(userEntity.getUserKey());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n플라워리스트 값 : "+flowerShopDto.getFlowerListGetFromFE());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n가게이름 값 : "+flowerShopDto.getShopName());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n위치 값 : "+flowerShopDto.getShopLocation());
        log.info("token값은 다음과 같습니다."+token);
        log.debug("---------------------------------------------------------------------");
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

    @GetMapping("/finished-products-in-shop")
    public List<FinishedProductDto> sellerViewFPinShop (@RequestHeader String token)
    {
        return flowerShopService.sellerViewFPinShop(token);
    }
}
