//package Unknown.PreScent.controller;
//
//
//import net.prescent.dto.PreScent.CustomerDto;
//import net.prescent.entity.PreScent.FinishedProductEntity;
//import Unknown.PreScent.service.CartService;
//import net.prescent.service.PreScent.CustomerService;
//import net.prescent.service.PreScent.FinishedProductService;
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
//        System.out.println("Cart start!!!----------------");
//        CustomerDto foundCustomer = customerService.findCustomer(customerKey);
//        FinishedProductEntity foundFp = finishedProductService.getFinishedProductWithFpKey(fpKey).get();
//
//        cartService.addCartItem(foundCustomer, foundFp, amount);
//
//        return "redirect:/item/view/{fpKey}";
//    }
//}
