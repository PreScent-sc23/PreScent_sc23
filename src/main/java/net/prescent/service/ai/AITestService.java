package net.prescent.service.ai;

import net.prescent.entity.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Service
public class AITestService {

    private final AIModelService aiModelService;

    @Autowired
    public AITestService(AIModelService aiModelService) {
        this.aiModelService = aiModelService;
    }

    public List<Object> processAdditionalImages() throws IOException {
        List<Object> images = new ArrayList<>();

        // image2.jpg 처리 (URL만 반환)
        String image2Url = aiModelService.uploadPredefinedFileToS3("159_2021042815384918.jpg");
        images.add(Map.of("url", aiModelService.getFileUrl(image2Url)));

        // image3.jpg, image4.jpg, image5.jpg, image6.jpg 처리 (URL, 이름, 꽃말 반환)
        List<ImageInfo> additionalImages = Arrays.asList(
                new ImageInfo("159_20210428153849183.jpg", "Daisy", "Lovely"),
                new ImageInfo("159_20210428153849184.jpg", "Gerbera", "Mysterious"),
                new ImageInfo("159_20210428153849185.jpg", "Rose", "Love"),
                new ImageInfo("159_20210428153849186.jpg", "Lily", "Purity")
        );

        for (ImageInfo image : additionalImages) {
            String fileKey = aiModelService.uploadPredefinedFileToS3(image.getUrl());
            image.setUrl(aiModelService.getFileUrl(fileKey));
            images.add(image);
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

