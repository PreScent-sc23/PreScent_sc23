package Unknown.PreScent.controller;

import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class SellerController {
    private SellerService sellerService;

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/signup")
    public String registerSeller(@ModelAttribute SellerDto sellerDto){
        sellerService.signup(sellerDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password,
                        HttpSession session, RedirectAttributes redirectAttributes){
        try {
            SellerDto loginResult = sellerService.login(id, password);
            session.setAttribute("loginSellerKey", loginResult.getSellerKey());
            return "redirect:/main";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("loginError", e.getMessage());
            return "redirect:/login";
        }
    }

}
