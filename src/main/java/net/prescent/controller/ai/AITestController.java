package net.prescent.controller.ai;

import net.prescent.entity.ImageInfo;
import net.prescent.service.ai.AIModelService;
import net.prescent.service.ai.AITestService;
import org.springframework.beans.factory.annotation.Value;
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

    private final AIModelService aiModelService;
    private final AITestService aiTestService;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    public AITestController(AIModelService s3Service, AITestService aiTestService) {
        this.aiModelService = s3Service;
        this.aiTestService = aiTestService;
    }


    @PostMapping(value = "/pslens", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadAndProcessImage(MultipartFile formData) {
        try {
            String fileKey = "uploads/" + UUID.randomUUID() + "-" + formData.getOriginalFilename();
            aiModelService.uploadFileToS3(formData, fileKey);
            String fileUrl = aiModelService.getFileUrl(fileKey);

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