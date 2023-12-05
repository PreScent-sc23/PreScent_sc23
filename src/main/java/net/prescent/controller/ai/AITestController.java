package net.prescent.controller.ai;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class AITestController {

    private final AIModelService aiModelService;
    private final AITestService aiTestService;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    public AITestController(AIModelService aiModelService, AITestService aiTestService) {
        this.aiModelService = aiModelService;
        this.aiTestService = aiTestService;
    }


    @PostMapping(value = "/pslens", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadAndProcessImage(MultipartFile file) {
        try {
            log.debug("Generated fileKey: " + file);
            String fileKey = "uploads/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
            log.debug("Generated fileKey: " + fileKey);
            aiModelService.uploadFileToS3(file, fileKey);

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