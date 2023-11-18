package Unknown.PreScent.controller;

import Unknown.PreScent.entity.FinishedProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Unknown.PreScent.service.FinishedProductService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/flower-shops/{shopKey}/finished-product")
public class FinishedProductController {

    private final FinishedProductService finishedProductService;

    @Autowired
    public FinishedProductController(FinishedProductService finishedProductService)
    {
        this.finishedProductService = finishedProductService;
    }

    @PostMapping("/add")
    public FinishedProductEntity addFinishedProduct(@RequestBody Integer shopKey, FinishedProductEntity finishedProductEntity)
    {
        return finishedProductService.addFinishedProduct(shopKey, finishedProductEntity);
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