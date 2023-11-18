package Unknown.PreScent.controller;

import Unknown.PreScent.entity.FinishedProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Unknown.PreScent.service.FinishedProductService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/finished-product")
public class FinishedProductController {

    private final FinishedProductService finishedProductService;

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

    @PostMapping("/add")
    public ResponseEntity<?> addFinishedProduct(@RequestBody FinishedProductEntity finishedProductEntity)
    {
        System.out.println("Result: " + finishedProductEntity.getFpFlowerList() + "// //" + finishedProductEntity.getFpDetail() + "\n");
        finishedProductService.addFinishedProduct(finishedProductEntity.getShopKey(),
                finishedProductEntity.getFpName(),
                finishedProductEntity.getFpTag(),
                finishedProductEntity.getFpPrice(),
                finishedProductEntity.getFpFlowerList(),
                finishedProductEntity.getFpDetail());

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