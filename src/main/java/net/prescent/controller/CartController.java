package net.prescent.controller;


import net.prescent.dto.CartItemAddRequestDto;
import net.prescent.dto.CartItemResponseDto;
import net.prescent.service.AccessTokenService;
import net.prescent.service.CartService;
import net.prescent.service.UserService;
import net.prescent.service.FinishedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("customer/cart")
public class CartController {


    private final CartService cartService;
    private final UserService userService;
    private final FinishedProductService finishedProductService;
    private final AccessTokenService accessTokenService;

    public CartController(CartService cartService, UserService userService, FinishedProductService finishedProductService, AccessTokenService accessTokenService) {
        this.cartService = cartService;
        this.userService = userService;
        this.finishedProductService = finishedProductService;
        this.accessTokenService = accessTokenService;
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addCartItem(@RequestHeader String Authorization, @RequestBody CartItemAddRequestDto cartItemAddRequestDto)
    {
        String token = Authorization.substring(7);
        cartItemAddRequestDto.setUserKey(accessTokenService.getUserFromToken(token).getUserKey());
        cartService.addToCart(cartItemAddRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/view-in-cart")
    public List<CartItemResponseDto> viewInCart(@RequestHeader String token)
    {
        Integer userKey = accessTokenService.getUserFromToken(token).getUserKey();
        return cartService.viewInCart(userKey);
    }

    @DeleteMapping("/clearCart")
    public ResponseEntity<?> clearCartItem(@RequestHeader String token)
    {
        Integer userKey = accessTokenService.getUserFromToken(token).getUserKey();
        cartService.clearCartItem(userKey);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/delete-cart-item")
    public ResponseEntity<?> deleteCartItem(@RequestParam Integer cartItemKey)
    {
        cartService.deleteCartItem(cartItemKey);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
