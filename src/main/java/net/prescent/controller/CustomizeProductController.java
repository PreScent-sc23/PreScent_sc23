//package Unknown.PreScent.controller;
//
//import Unknown.PreScent.dto.CustomizeProductDto;
//import Unknown.PreScent.repository.CustomizeProductFormRepository;
//import Unknown.PreScent.repository.CustomizeProductRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = "*")
//@RequestMapping("/customize-product")
//public class CustomizeProductController {
//
//    private final CustomizeProductRepository customizeProductRepository;
////    private final CustomizeProductFormRepository customizeProductFormRepo;
//
//    public CustomizeProductController(CustomizeProductRepository customizeProductRepository) {
//        this.customizeProductRepository = customizeProductRepository;
//    }
//
//    @GetMapping("/get")
//    public ResponseEntity<?> getCustomizeProduct(){
//
//
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @PostMapping("/order")
//    public ResponseEntity<?> orderCustomizeProduct(@RequestBody CustomizeProductDto customizeProductDto){
//
//
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//}
