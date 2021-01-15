package com.noirix.controller;

import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.exception.EntityNotFoundException;
import com.noirix.photos.AmazonUploadFileService;
import com.noirix.repository.impl.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/rest/photos/user")
@RequiredArgsConstructor
public class UserPhotoController {

  private final UserSpringDataRepository userRepository;

  private final AmazonUploadFileService amazonUploadFileService;

  @PostMapping("/{id}")
  public ResponseEntity<Map<Object, Object>> uploadUserPhoto(
      @PathVariable Long id, @RequestBody MultipartFile file) throws IOException {

    HibernateUser user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    byte[] imageBytes = file.getBytes();
    String imageLink = amazonUploadFileService.uploadFile(imageBytes, id);

    user.setPhotoLink(imageLink);
    userRepository.save(user);

    return new ResponseEntity<>(
        Collections.singletonMap("imageLink", imageLink), HttpStatus.CREATED);
  }
}
