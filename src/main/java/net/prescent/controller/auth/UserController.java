package net.prescent.controller.auth;

import lombok.RequiredArgsConstructor;
import net.prescent.dto.*;
import net.prescent.entity.AccessToken;
import net.prescent.entity.CustomerEntity;
import net.prescent.entity.UserEntity;
import net.prescent.repository.CustomerRepository;
import net.prescent.service.AccessTokenService;
import net.prescent.service.FlowerShopService;
import net.prescent.service.MailService;
import net.prescent.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AccessTokenService accessTokenService;
    private final FlowerShopService flowerShopService;
    private final CustomerRepository customerRepo;
    private final MailService mailService;

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
    public ResponseEntity<?> logout(@RequestHeader String Authorization)
    {
        String token = Authorization.substring(7);
        userService.logout(token);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customer-my-info")
    public CustomerDto getCustomerInfo(@RequestHeader String Authorization)
    {
        String token = Authorization.substring(7);
         return CustomerDto.toCustomerDto(accessTokenService.getCustomerFromToken(token));
    }

    @GetMapping("/seller-my-info")
    public SellerDto getSellerInfo(@RequestHeader String Authorization)
    {
        String token = Authorization.substring(7);
        return SellerDto.toSellerDto(accessTokenService.getSellerFromToken(token));
    }

    @PostMapping("set-location")
    public ResponseEntity<?> setLocation(@RequestHeader String Authorization, @RequestBody LocationDto locationDto)
    {
        String token = Authorization.substring(7);
        UserEntity userEntity = accessTokenService.getUserFromToken(token);
        if(customerRepo.findByUserKey(userEntity.getUserKey()).isPresent()) {
            System.out.println("여긴 유저키________________latitude는 다음과 같다: "+locationDto.getLatitude());
            System.out.println("여긴 유저키________________longitude는 다음과 같다: "+ locationDto.getLongitude());
            userService.setCustomerLocation(token, locationDto);
        }
        else {
            System.out.println("________________latitude는 다음과 같다: "+locationDto.getLatitude());
            System.out.println("________________longitude는 다음과 같다: "+ locationDto.getLongitude());
            flowerShopService.setShopLocation(token, locationDto);
        }
        return ResponseEntity.ok(locationDto);
    }

    @PostMapping("/send-verification-email")
    public ResponseEntity<?> sendVerificationEmail(@RequestBody String idEmail) {
        String verificationCode = UUID.randomUUID().toString().substring(0, 6);
        mailService.sendVerificationEmail(idEmail, verificationCode);
        return ResponseEntity.ok().body(Map.of("verificationCode", verificationCode));
    }
//    @PostMapping("/send-verification-email")
//    public ResponseEntity<?> sendVerificationEmail(@RequestBody String idEmail) {
//
//        userService.sendVerificationEmail(idEmail);
//        return ResponseEntity.ok().body("Verification email sent successfully");
//    }
//    @GetMapping("/verify-email")
//    public ResponseEntity<?> verifyEmail(@RequestParam String idEmail, @RequestParam String verificationCode) {
//        if (userService.verifyEmail(idEmail, verificationCode)) {
//            return ResponseEntity.ok().body("Email verified successfully");
//        } else {
//            return ResponseEntity.badRequest().body("Invalid verification code");
//        }
//    }
}
