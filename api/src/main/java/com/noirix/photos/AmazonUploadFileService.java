package com.noirix.photos;

public interface AmazonUploadFileService {
  String uploadFile(byte[] image, Long userId);
}
