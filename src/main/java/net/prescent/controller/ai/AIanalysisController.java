package net.prescent.controller.ai;

import lombok.extern.slf4j.Slf4j;
import net.prescent.service.ai.AITestService;
import net.prescent.service.ai.AIs3Service;
//import net.prescent.service.ai.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class AIanalysisController {

    private final AIs3Service aIs3Service;
    private final AITestService aiTestService;

    @Autowired
    public AIanalysisController(AIs3Service aIs3Service, AITestService aiTestService) {
        this.aIs3Service = aIs3Service;
        this.aiTestService = aiTestService;
    }

    @PostMapping(value = "/pslens", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadAndProcessImage(@RequestPart("pslens") MultipartFile file) {
        try {
            String fileKey = "uploads/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
            aIs3Service.uploadFileToS3(file, fileKey);
            String fileUrl = aIs3Service.getFileUrl(fileKey);

            Map<String, Object> uploadedImageResponse = new HashMap<>();
            if (fileUrl != null && !fileUrl.isEmpty()) {
                uploadedImageResponse.put("url", fileUrl);
            }

            List<Object> additionalImagesResponse = aiTestService.processAdditionalImages();
            uploadedImageResponse.put("additionalImages", additionalImagesResponse);

            return ResponseEntity.ok(uploadedImageResponse);
        } catch (IOException e) {
            // Log the exception and return an appropriate response
            log.error("Error processing image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image: " + e.getMessage());
        }
    }

}
