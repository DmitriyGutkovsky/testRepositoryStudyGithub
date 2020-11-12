package com.noirix.controller;

import com.noirix.controller.requests.CarCreateRequest;
import com.noirix.controller.requests.SearchCriteria;
import com.noirix.domain.Car;
import com.noirix.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/cars")
@RequiredArgsConstructor
public class CarRestController {

    public final CarService carService;

    //  // http://localhost:8080/rest/cars
    @GetMapping
    public ResponseEntity<List<Car>> findAllCars(){
        return ResponseEntity.ok(carService.findAll());
    }

    // http://localhost:8080/rest/cars/5
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Car findCarById(@PathVariable Long id){
        return carService.findById(id);
    }

    // http://localhost:8080/rest/cars/search?query=bmw
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> searchCar(@ModelAttribute SearchCriteria criteria){
        return carService.search(criteria.getQuery());
    }

    //  http://localhost:8080/rest/cars
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car carSaving(@RequestBody CarCreateRequest carCreateRequest) {
        Car car = new Car();

        car.setModel(carCreateRequest.getModel());
        car.setCreationYear(carCreateRequest.getCreationYear());
        car.setUserId(carCreateRequest.getUserId());
        car.setPrice(carCreateRequest.getPrice());
        car.setColor(carCreateRequest.getColor());

        return carService.save(car);
    }



}
