package net.prescent.controller.ai;

import net.prescent.entity.ImageInfo;
import net.prescent.service.ai.AITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AITestController {

    @Autowired
    private AITestService s3Service;

    @PostMapping("/pslens")
    public ResponseEntity<Map<String, Object>> uploadAndProcessImage(@RequestParam("file") MultipartFile file) {
        try {
            // Upload the file and get its URL
            String fileUrl = s3Service.uploadFileToS3(file);

            // Get additional image details
            List<ImageInfo> imageInfos = s3Service.getImageUrlsAndDetails();

            // Prepare the response
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("uploadedImageUrl", fileUrl);
            response.put("imageDetails", imageInfos);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            // Handle IOException (e.g., S3 upload failure)
            e.printStackTrace(); // Log the exception for debugging purposes
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
