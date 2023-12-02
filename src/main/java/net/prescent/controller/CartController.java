package net.prescent.controller;


import net.prescent.dto.CustomerDto;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.service.CartService;
import net.prescent.service.UserService;
import net.prescent.service.FinishedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private FinishedProductService finishedProductService;
    @Autowired
    private FinishedProductEntity finishedProductEntity;

    @PostMapping("/{customerKey}/{fpKey}")
    public String addCartItem(@PathVariable("customerKey") Integer customerKey, @PathVariable("fpKey") Integer fpKey, int amount) {
        CustomerDto foundCustomer = userService.findCustomer(customerKey);
        FinishedProductEntity foundFp = finishedProductService.getFinishedProductWithFpKey(fpKey).get();

        cartService.addCartItem(foundCustomer, foundFp, amount);

        return "redirect:/item/view/{fpKey}";
    }
}
