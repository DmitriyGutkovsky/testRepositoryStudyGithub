package com.noirix.controller;

import com.noirix.domain.hibernate.HibernateGood;
import com.noirix.repository.impl.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/goods")
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsRepository goodsRepository;

    @GetMapping
    public ResponseEntity<List<HibernateGood>> findAll() {
        return new ResponseEntity<>(goodsRepository.findAllWithCache(), HttpStatus.OK);
    }

}
