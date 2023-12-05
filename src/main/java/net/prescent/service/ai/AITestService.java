package net.prescent.service.ai;

import net.prescent.entity.ImageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AITestService {

    private final AIModelService aiModelService;

    public AITestService(AIModelService aiModelService) {
        this.aiModelService = aiModelService;
    }

    public String uploadFileToS3(MultipartFile file) throws IOException {
        // Generate a unique file key for each uploaded file
        String fileKey = "uploads/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
        // Upload the file and return its URL
        aiModelService.uploadFileToS3(file, fileKey);
        return aiModelService.getFileUrl(fileKey);
    }

    public List<ImageInfo> getImageUrlsAndDetails() {
        // Generate dummy URLs for demonstration; replace with your actual logic
        return Arrays.asList(
                new ImageInfo(aiModelService.getFileUrl("uploads/" + UUID.randomUUID() + "-daisy.jpg"), "Daisy", "Lovely"),
                new ImageInfo(aiModelService.getFileUrl("uploads/" + UUID.randomUUID() + "-gerbera.jpg"), "Gerbera", "Mysterious"),
                new ImageInfo(aiModelService.getFileUrl("uploads/" + UUID.randomUUID() + "-white_rose.jpg"), "White Rose", "Purity")
        );
    }
}

