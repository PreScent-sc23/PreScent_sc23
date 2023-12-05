package net.prescent.service.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class DockerService {

    @Value("${app.docker.volume.images}")
    private String imagesVolume;

    @Value("${app.docker.image-name}")
    private String dockerImageName;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${aws.accessKeyId}")
    private String awsAccessKeyId;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    public List<String> runYoloV5Container(String fileKey) {
        List<String> detectedImageKeys = new ArrayList<>();
        ProcessBuilder pb = new ProcessBuilder(
                "docker", "run", "--rm",
                "-e", "AWS_ACCESS_KEY_ID=" + awsAccessKeyId,
                "-e", "AWS_SECRET_ACCESS_KEY=" + awsSecretKey,
                "-e", "S3_BUCKET=" + bucket,
                dockerImageName,
                "--source", "s3://" + bucket + "/" + fileKey
        );

        try {
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Detected image key: ")) {
                        String detectedKey = line.split(": ")[1];
                        detectedImageKeys.add(detectedKey);
                    }
                }
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return detectedImageKeys;
    }
}