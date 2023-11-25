package net.prescent.controller.auth;

import net.prescent.dto.CustomerDto;
import net.prescent.dto.LoginRequest;
import net.prescent.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import net.prescent.service.AccessTokenService;
import net.prescent.service.CustomerService;
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
public class CustomerController {

    private final CustomerService customerService;
    private final AccessTokenService accessTokenService;
    private Logger log = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping("/customer/signup")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody CustomerDto customerDto,
                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.debug("BindingResult errors: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        log.debug("Proceeding with registration for: {}", customerDto);
        customerService.signup(customerDto);
        log.debug("customerService ended!");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/customer/login")  //token 방식으로 수정 후 /login으로 통합 예정
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        String token = customerService.login(loginRequest.getId(), loginRequest.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/customer/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        accessTokenService.deleteAccessToken(token);
        return ResponseEntity.ok().build();
    }
}