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

    @Autowired
    public AITestController(AIModelService aiModelService, AITestService aiTestService) {
        this.aiModelService = aiModelService;
        this.aiTestService = aiTestService;
    }

    @PostMapping(value = "/pslens", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadAndProcessImage(MultipartFile file) {
        try {
            // 이미지 처리는 하지 않고, 추가 이미지만 처리
            List<Object> response = aiTestService.processAdditionalImages();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error processing image: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image: " + e.getMessage());
        }
    }
}