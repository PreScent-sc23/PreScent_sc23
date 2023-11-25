package net.prescent.controller;

import net.prescent.dto.FinishedProductDto;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.service.FinishedProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/finished-product")
public class FinishedProductController {

    private final FinishedProductService finishedProductService;
    private Logger log;
    public FinishedProductController(FinishedProductService finishedProductService)
    {
        this.finishedProductService = finishedProductService;
    }

//    @PostMapping("/add")
//    public FinishedProductEntity addFinishedProduct(@RequestBody Integer shopKey, String fpName, String fpTag, String fpImage, Integer fpPrice, boolean fpState, String[] fpFlowerList)
//    {
//        return finishedProductService.addFinishedProduct(shopKey, fpName, fpTag, fpImage, fpPrice, fpState, fpFlowerList);
//    }

//    @PostMapping("/add")
//    public ResponseEntity<?> addFinishedProduct(@RequestBody FinishedProductEntity finishedProductEntity)
//    {
//        System.out.println("Result: " + finishedProductEntity.getFpFlowerList() + "// //" + finishedProductEntity.getFpDetail() + "\n");
//        finishedProductService.addFinishedProduct(finishedProductEntity.getShopKey(),
//                finishedProductEntity.getFpName(),
//                finishedProductEntity.getFpTag(),
//                finishedProductEntity.getFpPrice(),
//                finishedProductEntity.getFpFlowerList(),
//                finishedProductEntity.getFpDetail());
//
//        System.out.println(finishedProductService.getFinishedProductWithShopKey(0).get().get(0).getFpFlowerList());
//
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    } //old version

    @PostMapping("/add")
    public ResponseEntity<?> addFinishedProduct(FinishedProductDto finishedProductDto)
    {
        System.out.println("shopKey 값 : "+finishedProductDto.getShopKey()+"--------------------------------------------");
        System.out.println("fpName 값 : "+finishedProductDto.getFpName()+"--------------------------------------------");
        System.out.println("fpTag 값 : "+finishedProductDto.getFpTag()+"--------------------------------------------");
        System.out.println("fpPrice 값 : "+finishedProductDto.getFpPrice()+"--------------------------------------------");
        System.out.println("fpDetail 값 : "+finishedProductDto.getFpDetail()+"--------------------------------------------");
        System.out.println("fpFlowerList 값 : "+finishedProductDto.getFpFlowerList()+"--------------------------------------------");

        if (finishedProductDto.getFpImage() == null || finishedProductDto.getFpImage().isEmpty()) {
            System.out.println("file is not provided");
        }
        finishedProductService.addFinishedProduct(finishedProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{fpKey}")
    public Optional<FinishedProductEntity> getFinishedProductByFpKey(@PathVariable Integer fpKey)
    {
        return finishedProductService.getFinishedProductWithFpKey(fpKey);
    }
    @GetMapping("/{fpName}")
    public Optional<List<FinishedProductEntity>> getFinishedProductByFpName(@PathVariable String fpName)
    {
        return finishedProductService.getFinishedProductWithFpName(fpName);
    }
    @GetMapping("/{fpTag}")
    public Optional<List<FinishedProductEntity>> getFinishedProductByFpTag(@PathVariable String fpTag)
    {
        return finishedProductService.getFinishedProductWithFpTag(fpTag);
    }
//    @GetMapping("/{flower}")
//    public Optional<List<FinishedProduct>> getFinishedProductWithFlower(@PathVariable String flower)
//    {
//        return finishedProductService.getFinishedProductWithFlower(flower);
//    }
}