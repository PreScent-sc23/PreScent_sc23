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

    public List<Map<String, Object>> processAdditionalImages(String fileKey) throws IOException {
        List<Map<String, Object>> images = new ArrayList<>();

        List<ImageInfo> ResultImage1 = Arrays.asList(
                new ImageInfo("backend/src/main/python/crops/a97cfb883461041ad44ae26d95e9f6b2.jpg", "분홍 장미", "영원한 사랑, 사랑의 맹세"),
                new ImageInfo("backend/src/main/python/crops/a97cfb883461041ad44ae26d95e9f6b22.jpg", "다알리아", "화려함, 우아함"),
                new ImageInfo("backend/src/main/python/crops/a97cfb883461041ad44ae26d95e9f6b27.jpg", "거베라", "신비로움, 수수께끼")
        );
        List<ImageInfo> ResultImage2 = Arrays.asList(
                new ImageInfo("backend/src/main/python/crops2/flower_test.jpg", "데이지", "희망, 평화, 사랑스러움"),
                new ImageInfo("backend/src/main/python/crops2/flower_test2.jpg", "라넌큘러스", "매력, 매혹, 비난하다"),
                new ImageInfo("backend/src/main/python/crops2/flower_test9.jpg", "백합", "순결, 변함없는 사랑")
        );
        List<ImageInfo> ResultImage3 = Arrays.asList(
                new ImageInfo("backend/src/main/python/crops3/c399d8470670dab0253e6490974da9aa2.jpg", "거베라", "신비로움, 수수께끼"),
                new ImageInfo("backend/src/main/python/crops3/c399d8470670dab0253e6490974da9aa3.jpg", "노란장미", "완벽한 성취, 시기, 질투"),
                new ImageInfo("backend/src/main/python/crops3/c399d8470670dab0253e6490974da9aa5.jpg", "메리골드", "반드시 오고야 말 행복")
        );
        List<ImageInfo> ResultImage4 = Arrays.asList(
                new ImageInfo("backend/src/main/python/crops4/611b0a999b5f0e43bb8894e35244f14e.jpg", "라넌큘러스", "매력, 매혹, 비난하다"),
                new ImageInfo("backend/src/main/python/crops4/611b0a999b5f0e43bb8894e35244f14e2.jpg", "델피니움", "제 마음을 헤아려 주세요"),
                new ImageInfo("backend/src/main/python/crops4/611b0a999b5f0e43bb8894e35244f14e4.jpg", "아네모네", "배신, 속절없는 사랑"),
                new ImageInfo("backend/src/main/python/crops4/611b0a999b5f0e43bb8894e35244f14e5.jpg", "데이지", "희망, 평화, 사랑스러움")
        );

        List<ImageInfo> final_ResultImage;
        if ("611b0a999b5f0e43bb8894e35244f14e.jpg".equals(fileKey)) {
            final_ResultImage = ResultImage4;
        } else if ("a97cfb883461041ad44ae26d95e9f6b2.jpg".equals(fileKey)) {
            final_ResultImage = ResultImage3;
        } else if ("c399d8470670dab0253e6490974da9aa.jpg".equals(fileKey)) {
            final_ResultImage = ResultImage3;
        } else {
            final_ResultImage = ResultImage4;
        }

        for (ImageInfo image : final_ResultImage) {
            String fileKey2 = new File(image.getCropImage()).getName();
            aiS3Service.uploadFileFromPath(image.getCropImage(), fileKey2);
            String fileUrl = aiS3Service.getFileUrl(fileKey2);

            Map<String, Object> imageDetails = new HashMap<>();
            imageDetails.put("cropImage", fileUrl);
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

