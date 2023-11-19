package Unknown.PreScent.controller;

import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @GetMapping("/seller/signup")
    public String signup() { return "seller/signup"; }

    @PostMapping("/seller/signup")
    public ResponseEntity<?> registerSeller(@Valid @RequestBody SellerDto sellerDto,
                                            BindingResult bindingResult) {
        System.out.println("Enter registerSeller!!!");
        System.out.println("=>result: " + sellerDto.getSellerName());
        System.out.println("=>result: " + sellerDto.getSellerPassword());
        System.out.println("=>result: " + sellerDto.getSellerIdEmail());
        System.out.println("=>result: " + sellerDto.getSellerPhonenum());

        if (bindingResult.hasErrors()) {

            System.out.println("bindingResult error!!!");
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        System.out.println("sellerService start!!!");
        sellerService.signup(sellerDto);
        System.out.println("sellerService ended!!!");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/seller/login")
    public String login() { return "seller/login"; }

    @PostMapping("/seller/login")
    public ResponseEntity<Integer> login(@RequestParam String id, @RequestParam String password, HttpSession session) {
        SellerDto sellerDto = sellerService.login(id, password, session);
        return ResponseEntity.ok(sellerDto.getSellerKey());
    }

}
