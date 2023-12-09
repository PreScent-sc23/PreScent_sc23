package net.prescent.controller;

import lombok.extern.slf4j.Slf4j;
import net.prescent.dto.CustomizeProductDto;
import net.prescent.service.CustomizeProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;


@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customize-product")
public class CustomizeProductController {

    private final CustomizeProductService customizeProductService;

    public CustomizeProductController(CustomizeProductService customizeProductService) {
        this.customizeProductService = customizeProductService;
    }

    @PostMapping("add")
    public ResponseEntity<?> addCustomizeProduct(@RequestHeader String Authorization, @RequestBody CustomizeProductDto customizeProductDto)
    {
        String token = Authorization.substring(7);
        CustomizeProductDto returnCustomizeProductDto = customizeProductService.addCustomizeProduct(token, customizeProductDto);
        return ResponseEntity.ok(returnCustomizeProductDto);
    }

//    @GetMapping("view")
//    public
}