package net.prescent.service.ai;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import net.prescent.dto.FlowerDto;
import net.prescent.dto.FlowerResponse;
import net.prescent.entity.FlowerEntity;
import net.prescent.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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
public class AIModelService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 s3client;

    @Autowired
    public AIModelService(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public void uploadFileToS3(MultipartFile multipartFile, String fileKey) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        s3client.putObject(bucket, fileKey, multipartFile.getInputStream(), metadata);
    }

    public String uploadPredefinedFileToS3(String filePath) throws IOException {
        File file = new File(filePath);
        String fileKey = "predefined/" + file.getName();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.length());
        s3client.putObject(bucket, fileKey, new FileInputStream(file), metadata);
        return fileKey;
    }

    public String getFileUrl(String fileKey) {
        return s3client.getUrl(bucket, fileKey).toString();
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
