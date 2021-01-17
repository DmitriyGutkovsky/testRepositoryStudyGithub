package com.noirix.photos;

import com.noirix.config.AmazonConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AmazonUploadFileServiceImpl implements AmazonUploadFileService {

  private final S3Client s3Client;

  private final AmazonConfig amazonS3Config;

  @Override
  public String uploadFile(byte[] imageBytes, Long userId) {

    String imageUUID = UUID.randomUUID().toString(); // creating unique photo name

    s3Client.putObject(
        PutObjectRequest.builder()
            .bucket(amazonS3Config.getBucket()) // bucket to which file will be uploaded
            .contentType("image/jpeg") // type of uploaded file
            .contentLength((long) imageBytes.length) // size of uploaded file
            .acl(ObjectCannedACL.PUBLIC_READ) // granted permissions for current file
            .key(
                String.format(
                    "%s/%s/%s.jpg",
                    amazonS3Config.getUserFolder(),
                    userId, //папка, если ее нет, то она создасться
                    imageUUID)) // формирование ключа по которому будет храниться в bucket
            .build(),
        RequestBody.fromBytes(imageBytes));

    return String.format(
//        "%s/%s/%s/%s/%s.jpg",
        "%s/%s/%s/%s.jpg",
        amazonS3Config.getServerUrl(),
//        amazonS3Config.getBucket(), // returns correct link without it
        amazonS3Config.getUserFolder(),
        userId,
        imageUUID);
  }
}
