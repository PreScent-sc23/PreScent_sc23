package net.prescent.service.ai;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.prescent.dto.FlowerDto;
import net.prescent.dto.FlowerResponse;
import net.prescent.entity.FlowerEntity;
import net.prescent.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.synth.Region;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AIModelService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3Client amazonS3Client;

    @Autowired
    public AIModelService(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public String uploadPredefinedFileToS3(String filePath, String fileKey) throws IOException {
        File file = new File(filePath);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.length());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileKey, fileInputStream.toString())
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3Client.putObject(putObjectRequest);
        }
        return fileKey;
    }

    public String getFileUrl(String fileKey) {
        return "https://" + bucket + ".s3.amazonaws.com/" + fileKey;
    }
}


//@Service
//@RequiredArgsConstructor
//public class AIModelService {
//
//    @Value("${cloud.aws.s3.bucket}")
//    private String bucket;
//    private final AmazonS3 s3client;
//
//    public String uploadFileToS3(MultipartFile multipartFile) throws IOException {
//        String fileKey = "uploads/" + UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentLength(multipartFile.getSize());
//        s3client.putObject(bucket, fi
