package net.prescent.controller;

import net.prescent.dto.FPOrderCustomerDto;
import net.prescent.dto.FPOrderListDto;
import net.prescent.entity.FPOrderEntity;
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
    @Autowired
    private FPOrderService fpOrderService;


    @PostMapping("/customer/fp-order")
    public ResponseEntity<?> customerOrder(@RequestBody FPOrderCustomerDto fpOrderCustomerDto) {
        fpOrderService.customerOrderFinishedProduct(fpOrderCustomerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/customer/cart/order-in-cart")
    public ResponseEntity<?> customerOrderInCart(@RequestParam Integer userKey, @RequestBody String purchaseInfo)
    {
        fpOrderService.customerOrderInCart(userKey, purchaseInfo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("customer/fp-order-list")
    public ArrayList<FPOrderListDto> customerViewFPOrderList(@RequestParam Integer userKey){
        return fpOrderService.customerFPOrderList(userKey);
    }

    @GetMapping("seller/fp-order-list")
    public List<FPOrderListDto> sellerViewFPOrderList(@RequestParam Integer userKey){
        return fpOrderService.sellerFPOrderList(userKey);
    }

    @PostMapping("seller/fp-order-list/manage-order")
    public String sellerManageOrder(@RequestParam Integer userKey, @RequestParam Integer fpOrderKey, @RequestBody String state){
        return fpOrderService.sellerManageOrder(userKey,fpOrderKey, state);
    }
}
