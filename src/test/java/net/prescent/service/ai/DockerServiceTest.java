package net.prescent.service.ai;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DockerServiceTest {

    @Autowired
    private DockerService dockerService;

    @Autowired
    private AIModelService s3Service;

    @Test
    public void testRunYoloV5Container() throws Exception {

        String imageName = "testImage.jpg";
        byte[] imageContent = Files.readAllBytes(Paths.get("src/main/python/test.jpg"));
        MultipartFile multipartFile = new MockMultipartFile(imageName, imageName, "image/jpeg", imageContent);

        String fileKey = s3Service.uploadFileToS3(multipartFile);

        List<String> resultPaths = dockerService.runYoloV5Container(fileKey);

        assertFalse(resultPaths.isEmpty(), "No images were detected.");

        for (String path : resultPaths) {
            File resultFile = new File(path);
            assertTrue(resultFile.exists(), "Result image file does not exist: " + path);
        }
    }
}