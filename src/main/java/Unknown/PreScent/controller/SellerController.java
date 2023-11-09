package Unknown.PreScent.controller;

import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SellerController {
    private SellerService sellerService;

    @GetMapping("/seller/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/seller/signup")
    public String registerSeller(@Valid @ModelAttribute SellerDto sellerDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.sellerDto", bindingResult);
            redirectAttributes.addFlashAttribute("sellerDto", sellerDto);
            return "redirect:/seller/signup";
        }
        sellerService.signup(sellerDto);
        return "redirect:/seller/login";
    }

    @GetMapping("/seller/login")
    public String login() { return "seller/login"; }

    @PostMapping("/seller/login")
    public String login(@RequestParam String id, @RequestParam String password,
                        HttpSession session, RedirectAttributes redirectAttributes){
        try {
            SellerDto loginResult = sellerService.login(id, password);
            session.setAttribute("loginSellerKey", loginResult.getSellerKey());
            return "redirect:/seller/main";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("loginError", e.getMessage());
            return "redirect:/seller/login";
        }
    }

}
