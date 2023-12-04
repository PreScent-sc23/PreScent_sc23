//package net.prescent.controller;
//
//import net.prescent.dto.SellerDto;
//import net.prescent.service.SellerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//@RestController
//@CrossOrigin(origins = "*")
//@RequiredArgsConstructor
//public class SellerController {
//    @Autowired
//    private SellerService sellerService;
//
//    @GetMapping("/seller/signup")
//    public String signup(){
//        return "signup";
//    }
//
//    @PostMapping("/seller/signup")
//    public ResponseEntity<?> registerSeller(@Valid @RequestBody SellerDto sellerDto,
//                                            BindingResult bindingResult) {
//        log.debug("Enter registerSeller!!!");
//        log.debug("=>result: " + sellerDto.getSellerName());
//        log.debug("=>result: " + sellerDto.getSellerPassword());
//        log.debug("=>result: " + sellerDto.getSellerIdEmail());
//        log.debug("=>result: " + sellerDto.getSellerPhonenum());
//
//        if (bindingResult.hasErrors()) {
//
//            log.debug("bindingResult error!!!");
//            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
//        }
//        log.debug("sellerService start!!!");
//        sellerService.signup(sellerDto);
//        log.debug("sellerService ended!!!");
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @GetMapping("/seller/login")
//    public String login() { return "seller/login"; }
//
//    @PostMapping("/seller/login")
//    public String login(@RequestParam String id, @RequestParam String password,
//                        HttpSession session, RedirectAttributes redirectAttributes){
//        try {
//            String loginResult = sellerService.login(id, password);
//            session.setAttribute("loginSellerKey", loginResult);
//            return "redirect:/seller/main";
//        } catch (IllegalArgumentException e) {
//            redirectAttributes.addFlashAttribute("loginError", e.getMessage());
//            return "redirect:/seller/login";
//        }
//    }
//
//}
