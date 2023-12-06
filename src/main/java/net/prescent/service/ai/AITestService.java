package net.prescent.service.ai;

import lombok.extern.slf4j.Slf4j;
import net.prescent.entity.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;


@Service
@Slf4j
public class AITestService {

    private final AIs3Service aiS3Service;

    @Autowired
    public AITestService(AIs3Service aiS3Service) {
        this.aiS3Service = aiS3Service;
    }

    public List<Map<String, Object>> processAdditionalImages() throws IOException {
        List<Map<String, Object>> images = new ArrayList<>();

        List<ImageInfo> additionalImages = Arrays.asList(
                // Update these paths according to your project structure
                new ImageInfo("src/main/resources/images/daisy.jpg", "Daisy", "Lovely"),
                new ImageInfo("src/main/resources/images/gerbera.jpg", "Gerbera", "Mysterious"),
                new ImageInfo("src/main/resources/images/rose.jpg", "Rose", "Love"),
                new ImageInfo("src/main/resources/images/lily.jpg", "Lily", "Purity")
        );

        for (ImageInfo image : additionalImages) {
            String fileKey = new File(image.getUrl()).getName();
            aiS3Service.uploadFileFromPath(image.getUrl(), fileKey);
            String fileUrl = aiS3Service.getFileUrl(fileKey);

            Map<String, Object> imageDetails = new HashMap<>();
            imageDetails.put("url", fileUrl);
            imageDetails.put("name", image.getName());
            imageDetails.put("meaning", image.getMeaning());

            images.add(imageDetails);
        }

        return images;
    }
}
//@Service
//public class AITestService {
//
//    private final AIModelService aiModelService;
//
//    public AITestService(AIModelService aiModelService) {
//        this.aiModelService = aiModelService;
//    }
//
//    public String uploadFileToS3(MultipartFile file) throws IOException {
//        // Generate a unique file key for each uploaded file
//        String fileKey = "uploads/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
//        // Upload the file and return its URL
//        aiModelService.uploadFileToS3(file, fileKey);
//        return aiModelService.getFileUrl(fileKey);
//    }
//
//    public List<ImageInfo> getImageUrlsAndDetails() {
//        // Generate dummy URLs for demonstration; replace with your actual logic
//        return Arrays.asList(
//                new ImageInfo(aiModelService.getFileUrl("uploads/" + UUID.randomUUID() + "-daisy.jpg"), "Daisy", "Lovely"),
//                new ImageInfo(aiModelService.getFileUrl("uploads/" + UUID.randomUUID() + "-gerbera.jpg"), "Gerbera", "Mysterious"),
//                new ImageInfo(aiModelService.getFileUrl("uploads/" + UUID.randomUUID() + "-white_rose.jpg"), "White Rose", "Purity")
//        );
//    }
//}

