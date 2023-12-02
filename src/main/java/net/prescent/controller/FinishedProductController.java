package net.prescent.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import net.prescent.dto.FinishedProductDto;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.service.FinishedProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/finished-product")
public class FinishedProductController {

    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final FinishedProductService finishedProductService;
    public FinishedProductController(AmazonS3Client amazonS3Client, FinishedProductService finishedProductService)
    {
        this.amazonS3Client = amazonS3Client;
        this.finishedProductService = finishedProductService;
    }

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addFinishedProduct(HttpServletRequest httpServletRequest, @RequestParam("fpImage") MultipartFile fpImage,
            @RequestPart("finishedProduct") FinishedProductDto finishedProductDto)
    {
//        // 미완
//        @RequestPart("fpImage") MultipartFile fpImage,
//        @RequestPart("shopKey") Integer shopKey,
//        @RequestPart("fpName") String fpName,
//        @RequestPart("fpTag") String fpTag,
//        @RequestPart("fpPrice") Integer fpPrice,
//        @RequestPart("fpDetail") String fpDetail,
//        @RequestPart("fpFlowerList") String fpFlowerList
//        log.debug("shopKey 값 : "+finishedProductDto.getShopKey()+"--------------------------------------------");
//        log.debug("fpName 값 : "+finishedProductDto.getFpName()+"--------------------------------------------");
//        log.debug("fpTag 값 : "+finishedProductDto.getFpTag()+"--------------------------------------------");
//        log.debug("fpPrice 값 : "+finishedProductDto.getFpPrice()+"--------------------------------------------");
//        log.debug("fpDetail 값 : "+finishedProductDto.getFpDetail()+"--------------------------------------------");
//        log.debug("fpFlowerList 값 : "+finishedProductDto.getFpFlowerList()+"--------------------------------------------");

//        log.debug("shopKey 값 : "+ shopKey +"--------------------------------------------");
//        log.debug("fpName 값 : "+ fpName +"--------------------------------------------");
//        log.debug("fpTag 값 : "+ fpTag+"--------------------------------------------");
//        log.debug("fpPrice 값 : "+fpPrice+"--------------------------------------------");
//        log.debug("fpDetail 값 : "+fpDetail+"--------------------------------------------");
//        log.debug("fpFlowerList 값 : "+fpFlowerList+"--------------------------------------------");

        try {
            String fileName=fpImage.getOriginalFilename();
            log.debug(fileName);
            String fileUrl= amazonS3Client.getUrl(bucket, fileName).toString();
            log.debug(fileUrl);
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(fpImage.getContentType());
            metadata.setContentLength(fpImage.getSize());
            amazonS3Client.putObject(bucket,fileName,fpImage.getInputStream(),metadata);
//            FinishedProductDto finishedProductDto = new FinishedProductDto(shopKey, fpImage, fpName, fpTag, fpPrice, fpDetail, fpFlowerList);
//            if (finishedProductDto.getFpImage() == null || finishedProductDto.getFpImage().isEmpty()) {
//                log.debug("file is not provided");
//            }
            finishedProductDto.setFpImage(fileUrl);
            finishedProductService.addFinishedProduct(finishedProductDto);
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/key/{fpKey}")
    public Optional<FinishedProductEntity> getFinishedProductByFpKey(@PathVariable Integer fpKey)
    {
        return finishedProductService.getFinishedProductWithFpKey(fpKey);
    }
    @GetMapping("/name/{fpName}")
    public Optional<List<FinishedProductEntity>> getFinishedProductByFpName(@PathVariable String fpName)
    {
        return finishedProductService.getFinishedProductWithFpName(fpName);
    }
    @GetMapping("/tag/{fpTag}")
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