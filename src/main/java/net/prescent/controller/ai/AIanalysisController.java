package net.prescent.controller.ai;

import lombok.extern.slf4j.Slf4j;
import net.prescent.service.ai.AITestService;
import net.prescent.service.ai.AIs3Service;
//import net.prescent.service.ai.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    @PostMapping(value = "/pslens", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadAndProcessImage(@RequestPart("file") MultipartFile file) {
        try {
            String fileKey = file.getOriginalFilename();

            aIs3Service.uploadFileFromPath("backend/src/main/python/detects/", fileKey);
            String fileUrl = aIs3Service.getFileUrl(fileKey);

            List<Map<String, Object>> additionalImages = aiTestService.processAdditionalImages(fileKey);

            Map<String, Object> response = new HashMap<>();

            response.put("boundingImage", fileUrl);
            response.put("resultImage", additionalImages);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            log.error("Error processing image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image: " + e.getMessage());
        }
    }
}
