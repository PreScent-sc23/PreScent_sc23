package net.prescent.controller;

import net.prescent.dto.FPOrderCustomerDto;
import net.prescent.dto.FPOrderListDto;
import net.prescent.service.AccessTokenService;
import net.prescent.service.FPOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping()
public class FPOrderController {

    private final FPOrderService fpOrderService;
    private final AccessTokenService accessTokenService;

    public FPOrderController(FPOrderService fpOrderService, AccessTokenService accessTokenService) {
        this.fpOrderService = fpOrderService;
        this.accessTokenService = accessTokenService;
    }


    @PostMapping("/customer/fp-order")
    public ResponseEntity<?> customerOrder(@RequestHeader String Authorization, @RequestBody FPOrderCustomerDto fpOrderCustomerDto) {

        String token = Authorization.substring(7);
        fpOrderCustomerDto.setCustomerKey(accessTokenService.getCustomerFromToken(token).getUserKey());


        fpOrderService.customerOrderFinishedProduct(fpOrderCustomerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/customer/cart/order-in-cart")
    public ResponseEntity<?> customerOrderInCart(@RequestHeader String Authorization, @RequestBody String purchaseInfo)
    {
        String token = Authorization.substring(7);
        Integer userKey = accessTokenService.getUserFromToken(token).getUserKey();

        fpOrderService.customerOrderInCart(userKey, purchaseInfo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("customer/fp-order-list")
    public ArrayList<FPOrderListDto> customerViewFPOrderList(@RequestHeader String Authorization)
    {
        String token = Authorization.substring(7);
        Integer userKey = accessTokenService.getUserFromToken(token).getUserKey();

        return fpOrderService.customerFPOrderList(userKey);
    }
    @GetMapping("seller/fp-order-list/waiting")
    public List<FPOrderListDto> sellerViewWaitingFPOrderList(@RequestHeader String Authorization)
    {
        String token = Authorization.substring(7);
        Integer userKey = accessTokenService.getUserFromToken(token).getUserKey();
        return fpOrderService.sellerWaitingFPOrderList(userKey);

    }
    @GetMapping("seller/fp-order-list/complete")
    public List<FPOrderListDto> sellerViewCompleteFPOrderList(@RequestHeader String Authorization)
    {
        String token = Authorization.substring(7);
        Integer userKey = accessTokenService.getUserFromToken(token).getUserKey();
        return fpOrderService.sellerCompleteFPOrderList(userKey);
    }

    @GetMapping("customer/fp-order-list/waiting")
    public List<FPOrderListDto> customerViewWaitingFPOrderList(@RequestHeader String Authorization)
    {
        String token = Authorization.substring(7);
        Integer userKey = accessTokenService.getUserFromToken(token).getUserKey();
        return fpOrderService.customerWaitingFPOrderList(userKey);

    }
    @GetMapping("customer/fp-order-list/complete")
    public List<FPOrderListDto> customerViewCompleteFPOrderList(@RequestHeader String Authorization)
    {
        String token = Authorization.substring(7);
        Integer userKey = accessTokenService.getUserFromToken(token).getUserKey();
        return fpOrderService.customerCompleteFPOrderList(userKey);
    }
    @PutMapping("seller/fp-order-list/set-complete")
    public String sellerSetOrderComplete(@RequestHeader String Authorization, @RequestParam Integer fpOrderKey){

        String token = Authorization.substring(7);
        Integer userKey = accessTokenService.getUserFromToken(token).getUserKey();

        return fpOrderService.sellerSetOrder(userKey,fpOrderKey, "픽업 완료");
    }
    @PutMapping("seller/fp-order-list/set-waiting")
    public String sellerSetOrderWaiting(@RequestHeader String Authorization, @RequestParam Integer fpOrderKey){

        String token = Authorization.substring(7);
        Integer userKey = accessTokenService.getUserFromToken(token).getUserKey();

        return fpOrderService.sellerSetOrder(userKey,fpOrderKey, "픽업 대기중");
    }
}
