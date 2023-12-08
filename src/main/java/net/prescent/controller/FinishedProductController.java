package net.prescent.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import net.prescent.dto.FinishedProductDto;
import net.prescent.entity.FinishedProductEntity;
import net.prescent.service.AccessTokenService;
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

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/finished-product")
public class FinishedProductController {

    private final AccessTokenService accessTokenService;
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final FinishedProductService finishedProductService;
    public FinishedProductController(AccessTokenService accessTokenService, AmazonS3Client amazonS3Client, FinishedProductService finishedProductService)
    {
        this.accessTokenService = accessTokenService;
        this.amazonS3Client = amazonS3Client;
        this.finishedProductService = finishedProductService;
    }

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addFinishedProduct(@RequestHeader String Authorization, HttpServletRequest httpServletRequest, @RequestParam("fpImage") MultipartFile fpImage,
            @RequestPart("finishedProduct") FinishedProductDto finishedProductDto)
    {
        String token = Authorization.substring(7);
        Integer shopKey = accessTokenService.getSellerFromToken(token).getFlowerShopEntity().getShopKey();
        finishedProductDto.setShopKey(shopKey);

        try {
            String fileName=fpImage.getOriginalFilename();
            log.debug(fileName);
            String fileUrl= amazonS3Client.getUrl(bucket, fileName).toString();
            log.debug(fileUrl);
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(fpImage.getContentType());
            metadata.setContentLength(fpImage.getSize());
            amazonS3Client.putObject(bucket,fileName,fpImage.getInputStream(),metadata);

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