package com.noirix.controller;

import com.noirix.domain.Car;
import com.noirix.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/cars")
@RequiredArgsConstructor
public class CarRestController {

    public final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> findAllCars(){
        return ResponseEntity.ok(carService.findAll());
    }

}
