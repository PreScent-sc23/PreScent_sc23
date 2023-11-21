package Unknown.PreScent.controller;

import Unknown.PreScent.dto.FinishedProductDto;
import Unknown.PreScent.entity.FinishedProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Unknown.PreScent.service.FinishedProductService;
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
    @Autowired
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
    public FinishedProductEntity addFinishedProduct(@RequestParam Integer shopKey, @RequestPart(name = "fpImage", required = false) MultipartFile fpImage, @RequestParam("fpName") String fpName, @RequestParam("fpTag") String fpTag, @RequestParam("fpPrice") Integer fpPrice, @RequestParam("fpDetail") String fpDetail, @RequestParam("fpFlowerList") String fpFlowerList)
    {
        System.out.println("shopKey 값 : "+shopKey+"--------------------------------------------");
        System.out.println("fpName 값 : "+fpName+"--------------------------------------------");
        System.out.println("fpTag 값 : "+fpTag+"--------------------------------------------");
        System.out.println("fpPrice 값 : "+fpPrice+"--------------------------------------------");
        System.out.println("fpDetail 값 : "+fpDetail+"--------------------------------------------");
        System.out.println("fpFlowerList 값 : "+fpFlowerList+"--------------------------------------------");

        if (fpImage != null && !fpImage.isEmpty()) {
            System.out.println("file is not provided");
        }
        String[] fpFlowerListToStringArray = fpFlowerList.split(",");
        FinishedProductDto finishedProductDto = new FinishedProductDto(fpImage, fpName, fpTag, fpPrice, fpDetail, fpFlowerListToStringArray);
        return finishedProductService.addFinishedProduct(shopKey, finishedProductDto);
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