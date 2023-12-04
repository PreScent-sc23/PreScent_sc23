package net.prescent.controller;


import net.prescent.dto.CartItemAddRequestDto;
import net.prescent.dto.CartItemResponseDto;
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

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addCartItem(@RequestBody CartItemAddRequestDto cartItemAddRequestDto) {
        cartService.addToCart(cartItemAddRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/view-in-cart")
    public CartResponseDto viewInCart(@RequestParam Integer userKey) {
        return cartService.viewInCart(userKey);
    }

    @DeleteMapping()
    public ResponseEntity<?> clearCartItem(@RequestBody Integer userKey) {
        cartService.clearCartItem(userKey);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}