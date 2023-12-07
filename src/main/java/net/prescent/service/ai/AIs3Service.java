package net.prescent.service.ai;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class AIs3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3Client amazonS3Client;

    @Autowired
    public AIs3Service(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public void uploadFileToS3(MultipartFile multipartFile, String fileKey) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileKey);
        multipartFile.transferTo(convFile);

        try (FileInputStream fileInputStream = new FileInputStream(convFile)) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(convFile.length());
            amazonS3Client.putObject(bucket, fileKey, fileInputStream, metadata);
        }
    }
    public void uploadFileFromPath(String filePath, String fileKey) throws IOException {
        File file = new File(filePath);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.length());
            amazonS3Client.putObject(bucket, fileKey, fileInputStream, metadata);
        }
    }

    public String getFileUrl(String fileKey) {
        return "https://" + bucket + ".s3.amazonaws.com/" + fileKey;
    }
}