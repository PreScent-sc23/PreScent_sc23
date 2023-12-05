package net.prescent.controller.ai;

import net.prescent.entity.ImageInfo;
import net.prescent.service.ai.AIModelService;
import net.prescent.service.ai.AITestService;
import net.prescent.service.ai.DockerService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class AITestController {

    private final AIModelService s3Service;
    private final AITestService aiTestService;

    @Autowired
    public AITestController(AIModelService s3Service, AITestService aiTestService) {
        this.s3Service = s3Service;
        this.aiTestService = aiTestService;
    }

    @PostMapping("/pslens")
    public ResponseEntity<?> uploadAndProcessImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileKey = "uploads/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
            s3Service.uploadFileToS3(file, fileKey);
            String fileUrl = s3Service.getFileUrl(fileKey);

            List<Object> response = new ArrayList<>();
            if (fileUrl != null && !fileUrl.isEmpty()) {
                response.add(Map.of("url", fileUrl));
            }

            response.addAll(aiTestService.processAdditionalImages());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image: " + e.getMessage());
        }
    }
}