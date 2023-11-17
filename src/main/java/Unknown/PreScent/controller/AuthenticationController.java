//package Unknown.PreScent.controller;
//
//import Unknown.PreScent.dto.SellerDto;
//import Unknown.PreScent.dto.SellerLoginRequestDto;
//import Unknown.PreScent.repository.SellerRepository;
//import Unknown.PreScent.service.JwtUserDetailsService;
//import Unknown.PreScent.jwt.JwtTokenUtil;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthenticationController {
//
//    protected final Log logger = LogFactory.getLog(getClass());
//
//    @Autowired
//    final SellerRepository sellerRepo;
//    final AuthenticationManager authenticationManager;
//    @Autowired
//    final JwtUserDetailsService userDetailsService;
//    @Autowired
//    final JwtTokenUtil jwtTokenUtil;
//
//    public AuthenticationController(SellerRepository sellerRepo, AuthenticationManager authenticationManager,
//                                    JwtUserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
//        this.sellerRepo = sellerRepo;
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestParam SellerLoginRequestDto sellerLoginRequestDto) {
//        Map<String, Object> responseMap = new HashMap<>();
//        try {
//            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sellerLoginRequestDto.getSellerIdEmail()
//                    , sellerLoginRequestDto.getSellerPassword()));
//            if (auth.isAuthenticated()) {
//                logger.info("Logged In");
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                String token = jwtTokenUtil.generateToken(userDetails);
//                responseMap.put("error", false);
//                responseMap.put("message", "Logged In");
//                responseMap.put("token", token);
//                return ResponseEntity.ok(responseMap);
//            } else {
//                responseMap.put("error", true);
//                responseMap.put("message", "Invalid Credentials");
//                return ResponseEntity.status(401).body(responseMap);
//            }
//        } catch (DisabledException e) {
//            e.printStackTrace();
//            responseMap.put("error", true);
//            responseMap.put("message", "User is disabled");
//            return ResponseEntity.status(500).body(responseMap);
//        } catch (BadCredentialsException e) {
//            responseMap.put("error", true);
//            responseMap.put("message", "Invalid Credentials");
//            return ResponseEntity.status(401).body(responseMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseMap.put("error", true);
//            responseMap.put("message", "Something went wrong");
//            return ResponseEntity.status(500).body(responseMap);
//        }
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> saveUser(@RequestParam("first_name") String firstName,
//                                      @RequestParam("last_name") String lastName,
//                                      @RequestParam("user_name") String userName, @RequestParam("email") String email
//            , @RequestParam("password") String password) {
//        Map<String, Object> responseMap = new HashMap<>();
//        User user = new User();
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setEmail(email);
//        user.setPassword(new BCryptPasswordEncoder().encode(password));
//        user.setRole("USER");
//        user.setUserName(userName);
//        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//        String token = jwtTokenUtil.generateToken(userDetails);
//        userRepository.save(user);
//        responseMap.put("error", false);
//        responseMap.put("username", userName);
//        responseMap.put("message", "Account created successfully");
//        responseMap.put("token", token);
//        return ResponseEntity.ok(responseMap);
//    }
//}