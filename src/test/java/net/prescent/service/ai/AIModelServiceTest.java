package net.prescent.service.ai;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AIModelServiceTest {

    @Mock
    private AIModelService s3client;

    @InjectMocks
    private AIModelService service;

    @Test
    public void testUploadFileToS3() throws IOException {
        MultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "Hello, World!".getBytes());
        String fileKey = "uploads/test.jpg";
        when(s3client.putObject(anyString(), anyString(), any(InputStream.class), any(ObjectMetadata.class))).thenReturn(null);

        String result = service.uploadFileToS3(file);

        assertEquals(fileKey, result);
    }

    @Test
    public void testGetFileUrl() throws MalformedURLException {
        String fileKey = "uploads/test.jpg";
        String fileUrl = "http://s3.amazonaws.com/bucket/uploads/test.jpg";
        when(s3client.getUrl(anyString(), anyString())).thenReturn(new URL(fileUrl));

        String result = service.getFileUrl(fileKey);

        assertEquals(fileUrl, result);
    }
}
