package net.prescent.controller.auth;

import net.prescent.dto.SellerDto;
import net.prescent.dto.LoginResponse;
import net.prescent.dto.LoginRequest;
import net.prescent.service.AccessTokenService;
import net.prescent.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;
    private final AccessTokenService accessTokenService;
    private Logger log = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping("/seller/signup")
    public ResponseEntity<?> registerSeller(@Valid @RequestBody SellerDto sellerDto,
                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.debug("BindingResult errors: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        log.debug("Proceeding with registration for: {}", sellerDto);
        sellerService.signup(sellerDto);
        log.debug("sellerService ended!");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        String token = sellerService.login(loginRequest.getId(), loginRequest.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        accessTokenService.deleteAccessToken(token);
        return ResponseEntity.ok().build();
    }
     */
}