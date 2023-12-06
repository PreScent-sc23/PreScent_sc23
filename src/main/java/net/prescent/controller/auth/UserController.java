package net.prescent.controller.auth;

import lombok.RequiredArgsConstructor;
import net.prescent.dto.CustomerDto;
import net.prescent.dto.LoginRequest;
import net.prescent.dto.LoginResponse;
import net.prescent.dto.SellerDto;
import net.prescent.entity.AccessToken;
import net.prescent.service.AccessTokenService;
import net.prescent.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AccessTokenService accessTokenService;

    @PostMapping("/customer/signup")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        userService.signupCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/seller/signup")
    public ResponseEntity<?> registerSeller(@Valid @RequestBody SellerDto sellerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        userService.signupSeller(sellerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        String token = userService.login(loginRequest.getIdEmail(), loginRequest.getPassword());
        HttpHeaders headers = new HttpHeaders();
        Integer role = userService.getRole(loginRequest.getIdEmail());
        headers.add("Authorization", "Bearer " + token);
        return new ResponseEntity<>(new LoginResponse(token,role), headers, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        userService.logout(token);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customer-my-info")
    public CustomerDto getCustomerInfo(@RequestHeader("Authorization") String token) {
         return CustomerDto.toCustomerDto(accessTokenService.getCustomerFromToken(token));
    }

    @GetMapping("/seller-my-info")
    public SellerDto getSellerInfo(@RequestHeader("Authorization") String token) {
        return SellerDto.toSellerDto(accessTokenService.getSellerFromToken(token));
    }
}
