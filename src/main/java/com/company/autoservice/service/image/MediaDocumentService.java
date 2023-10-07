package com.company.autoservice.service.image;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

@Service
public class MediaDocumentService {
    public final static String BUCKET_NAME = "sheengogram.appspot.com";
    public final static String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/" + BUCKET_NAME + "/o/%s?alt=media";

    //TODO: Change this path to your own path
//    public final static String FIREBASE_TOKEN_PATH = "/home/developer/Desktop/Java Backend/projects/spring/uzumMarket/src/main/resources/credentials.json";
    public final static String FIREBASE_TOKEN_PATH = "C:\\JAVA\\RealProjects\\Auto-Service\\src\\main\\resources\\credentials.json";

    @SneakyThrows
    public String upload(MultipartFile file) {
        String fileName = UUID.randomUUID() + file.getOriginalFilename();
        StorageOptions
                .newBuilder()
                .setCredentials(getCredentials())
                .build()
                .getService()
                .create(getBlobInfo(fileName, file.getContentType()), file.getBytes());
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    @SneakyThrows
    private Credentials getCredentials() {
        File file = new File(FIREBASE_TOKEN_PATH);
        return GoogleCredentials.fromStream(new FileInputStream(file));
    }

    private BlobInfo getBlobInfo(String fileUniqueId, String contentType) {
        return BlobInfo
                .newBuilder(BlobId.of(BUCKET_NAME, fileUniqueId))
                .setContentType(Objects.isNull(contentType) ? "media" : contentType)
                .build();
    }

}