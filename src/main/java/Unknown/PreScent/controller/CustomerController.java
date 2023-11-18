package Unknown.PreScent.controller;

import Unknown.PreScent.dto.CustomerDto;
import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.service.CustomerService;
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
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/signup")
    public String signup(){
        return "customer/signup";
    }

    @PostMapping("/customer/signup")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody CustomerDto customerDto,
                                              BindingResult bindingResult) {
        System.out.println("Enter registerCustomer!!!");
        System.out.println("=>result: " + customerDto.getCustomerName());
        System.out.println("=>result: " + customerDto.getCustomerPassword());
        System.out.println("=>result: " + customerDto.getCustomerIdEmail());
        System.out.println("=>result: " + customerDto.getCustomerPhonenum());

        if (bindingResult.hasErrors()) {

            System.out.println("bindingResult error!!!");
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        System.out.println("customerService start!!!");
        customerService.signup(customerDto);
        System.out.println("customerService ended!!!");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/customer/login")
    public String login() { return "customer/login"; }

    @PostMapping("/customer/login")
    public String login(@RequestParam String id, @RequestParam String password,
                        HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            CustomerDto loginResult = customerService.login(id, password);
            session.setAttribute("loginCustomerIdEmail", loginResult.getCustomerIdEmail());

            if (loginResult.getCustomerLocation() == null) {
                return "redirect:/customer/set-location";
            } else {
                return "redirect:/customer/main";
            }
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("loginError", e.getMessage());
            return "redirect:/customer/login";
        }
    }
}
