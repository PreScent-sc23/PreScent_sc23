package net.prescent.controller;


import net.prescent.dto.CartResponseDto;
import net.prescent.service.CartService;
import net.prescent.service.UserService;
import net.prescent.service.FinishedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("customer/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private FinishedProductService finishedProductService;

    @PostMapping("/addToCart")
    public ResponseEntity<?> addCartItem(@RequestParam("userKey") Integer userKey, @RequestParam Integer fpKey, int amount, String pickupDate, String pickupTime) {
        cartService.addToCart(userKey, fpKey, amount,pickupDate, pickupTime);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public CartResponseDto viewInCart(@RequestParam("userKey") Integer userKey)
    {
        return cartService.viewInCart(userKey);
    }

    @DeleteMapping()
    public ResponseEntity<?> clearCartItem(@RequestParam("userKey")Integer userKey)
    {
        cartService.clearCartItem(userKey);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
