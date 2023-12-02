package net.prescent.controller;

import net.prescent.dto.FPOrderCustomerDto;
import net.prescent.dto.FPOrderListDto;
import net.prescent.entity.FPOrderEntity;
import net.prescent.service.FPOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping()
public class FPOrderController {
    @Autowired
    private FPOrderService fpOrderService;


    @PostMapping("/customer/fp-order")
    public FPOrderEntity customerOrder(@RequestBody FPOrderCustomerDto fpOrderCustomerDto) {
        return fpOrderService.customerOrderFinishedProduct(fpOrderCustomerDto);
    }

    @GetMapping("customer/fp-order-list")
    public ArrayList<FPOrderListDto> customerFPOrderList(@RequestParam Integer custKey){
        return fpOrderService.customerFPOrderList(custKey);
    }
}
