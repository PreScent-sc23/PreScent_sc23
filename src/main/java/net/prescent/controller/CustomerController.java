package net.prescent.controller;

import net.prescent.dto.CustomerDto;
import net.prescent.dto.CustomerLoginRequestDto;
import net.prescent.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/customer/signup")
    public String signup(){
        return "customer/signup";
    }

    @PostMapping("/customer/signup")
    public String registerCustomer(@Valid @ModelAttribute CustomerDto customerDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.customerDto", bindingResult);
            redirectAttributes.addFlashAttribute("customerDto", customerDto);
            return "redirect:/customer/signup";
        }
        customerService.signup(customerDto);
        return "redirect:/customer/login";
    }

    @GetMapping("/customer/login")
    public String login() { return "customer/login"; }

    @PostMapping("/customer/login")
    public String login(@RequestParam CustomerLoginRequestDto customerLoginRequestDto ,
                        HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            CustomerDto loginResult = customerService.login(customerLoginRequestDto.getSellerIdEmail(), customerLoginRequestDto.getSellerPassword());
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
