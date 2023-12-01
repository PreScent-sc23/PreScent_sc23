package Unknown.PreScent.controller;

import Unknown.PreScent.dto.CustomizeProductFormDto;
import Unknown.PreScent.service.CustomizeProductFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customize-product-form")
public class CustomizeProductFormController {

    private final CustomizeProductFormService customizeProductFormService;
    @Autowired
    public CustomizeProductFormController(CustomizeProductFormService customizeProductFormService) {
        this.customizeProductFormService = customizeProductFormService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCustomizeProductForm(){
//        customizeProductFormService

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCustomizeProductForm(@RequestBody CustomizeProductFormDto customizeProductFormDto){
        customizeProductFormService.addCustomizeProductForm(customizeProductFormDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
