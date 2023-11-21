package Unknown.PreScent.controller;

import Unknown.PreScent.dto.FPOrderCustomerDto;
import Unknown.PreScent.entity.FPOrderEntity;
import Unknown.PreScent.entity.FlowerShopEntity;
import Unknown.PreScent.service.FPOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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