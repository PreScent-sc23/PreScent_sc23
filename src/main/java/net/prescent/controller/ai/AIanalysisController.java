package net.prescent.controller.ai;

import net.prescent.service.ai.AIModelService;
import net.prescent.service.ai.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class AIanalysisController {

    private final AIModelService s3Service;
    private final DockerService dockerService;

    @Autowired
    public AIanalysisController(AIModelService s3Service, DockerService dockerService) {
        this.s3Service = s3Service;
        this.dockerService = dockerService;
    }

    @PostMapping("/ai-pslens")
    public ResponseEntity<?> uploadAndProcessImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileKey = s3Service.uploadFileToS3(file);
            List<String> detectedImageKeys = dockerService.runYoloV5Container(fileKey);

            List<String> detectedImageUrls = detectedImageKeys.stream()
                    .map(s3Service::getFileUrl)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(detectedImageUrls);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image");
        }
    }
}
