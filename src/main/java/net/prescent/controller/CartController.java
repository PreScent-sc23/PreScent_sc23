//package Unknown.PreScent.controller;
//
//
//import dto.net.prescent.CustomerDto;
//import entity.net.prescent.FinishedProductEntity;
//import Unknown.PreScent.service.CartService;
//import service.net.prescent.CustomerService;
//import service.net.prescent.FinishedProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@CrossOrigin(origins = "*")
//@RequestMapping("/Cart")
//public class CartController {
//
//    @Autowired
//    private CartService cartService;
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    private FinishedProductService finishedProductService;
//    @Autowired
//    private FinishedProductEntity finishedProductEntity;
//
//    @PostMapping("/{customerKey}/{fpKey}")
//    public String addCartItem(@PathVariable("customerKey") Integer customerKey, @PathVariable("fpKey") Integer fpKey, int amount) {
//        CustomerDto foundCustomer = customerService.findCustomer(customerKey);
//        FinishedProductEntity foundFp = finishedProductService.getFinishedProductWithFpKey(fpKey).get();
//
//        cartService.addCartItem(foundCustomer, foundFp, amount);
//
//        return "redirect:/item/view/{fpKey}";
//    }
//}
