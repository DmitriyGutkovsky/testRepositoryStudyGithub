package com.noirix.controller;

import com.noirix.config.AmazonConfig;
import com.noirix.config.GoogleConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/google")
public class GoogleRestController {

  public final GoogleConfig googleConfig;

  public final AmazonConfig amazonConfig;

  @GetMapping
  public ResponseEntity<Map<String, String>> getConfig() {

    Map<String, String> map = new LinkedHashMap<>();
    map.put("s3", amazonConfig.getS3());
    map.put("token", amazonConfig.getToken());
    map.put("password", amazonConfig.getPassword());

    map.put("login", googleConfig.getLogin());
    map.put("pincode", googleConfig.getPincode());
    map.put("location", googleConfig.getLocation());

    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @GetMapping("/config")
  public ResponseEntity<List<String>> getConfigAll() {
    List<String> list = new LinkedList<>();
    list.add(googleConfig.toString());
    list.add(amazonConfig.toString());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }
}
