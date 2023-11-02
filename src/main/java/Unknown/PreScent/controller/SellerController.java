package Unknown.PreScent.controller;

import Unknown.PreScent.dto.SellerDto;
import Unknown.PreScent.service.SellerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class SellerController {
    private SellerService sellerService;

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/signup")
    public String registerSeller(SellerDto sellerDto){
        sellerService.signup(sellerDto);
        return "redirect:/login";
    }
}
