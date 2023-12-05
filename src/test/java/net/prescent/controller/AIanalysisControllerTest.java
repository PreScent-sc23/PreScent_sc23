package net.prescent.controller;

import net.prescent.controller.ai.AIanalysisController;
import net.prescent.service.ai.AIModelService;
import net.prescent.service.ai.DockerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AIanalysisControllerTest {

    @Mock
    private AIModelService s3Service;

    @Mock
    private DockerService dockerService;

    @InjectMocks
    private AIanalysisController controller;

    @Test
    @DisplayName("이미지 업로드, url 처리 완료")
    public void testUploadAndProcessImage() throws IOException {

        MultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "Hello, World!".getBytes());
        String fileKey = "uploads/test.jpg";
        when(s3Service.uploadFileToS3(file)).thenReturn(fileKey);

        List<String> detectedImageKeys = Arrays.asList("key1", "key2");
        when(dockerService.runYoloV5Container(fileKey)).thenReturn(detectedImageKeys);

        List<String> detectedImageUrls = Arrays.asList("url1", "url2");
        when(s3Service.getFileUrl("key1")).thenReturn(detectedImageUrls.get(0));
        when(s3Service.getFileUrl("key2")).thenReturn(detectedImageUrls.get(1));

        ResponseEntity<?> response = controller.uploadAndProcessImage(file);
        assertEquals(ResponseEntity.ok(detectedImageUrls), response);
    }
}
