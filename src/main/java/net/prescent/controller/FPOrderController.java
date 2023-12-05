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

    @GetMapping("customer/fp-order-list")
    public ArrayList<FPOrderListDto> customerFPOrderList(@RequestParam Integer userKey){
        return fpOrderService.customerFPOrderList(userKey);
    }

    @PostMapping("/customer/cart/order-in-cart")
    public ResponseEntity<?> customerOrderInCart(@RequestBody Integer userKey, @RequestBody String purchaseInfo)
    {
        fpOrderService.customerOrderInCart(userKey, purchaseInfo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("seller/fp-order-list")
    public List<FPOrderListDto> sellerFPOrderList(@RequestParam Integer userKey){
        return fpOrderService.sellerFPOrderList(userKey);
    }

    @PostMapping("seller/fp-order-list/manage-order")
    public String sellerManageOrder(@RequestBody Integer userKey, @RequestBody Integer fpOrderKey, @RequestBody String state){
        return fpOrderService.sellerManageOrder(userKey,fpOrderKey, state);
    }
}
