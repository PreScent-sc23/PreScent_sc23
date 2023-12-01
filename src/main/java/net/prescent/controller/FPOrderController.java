package net.prescent.controller;

import net.prescent.dto.FPOrderCustomerDto;
import net.prescent.entity.FPOrderEntity;
import net.prescent.service.FPOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/flower-shops/finished-products")
public class FPOrderController {
    @Autowired
    private FPOrderService fpOrderService;


    @PostMapping("/customer-order")
    public FPOrderEntity customerOrder(@RequestBody FPOrderCustomerDto fpOrderCustomerDto) {
        return fpOrderService.customerOrderFinishedProduct(fpOrderCustomerDto);
    }
}
