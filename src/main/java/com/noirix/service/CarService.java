package com.noirix.service;

import com.noirix.domain.Car;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Car save(Car user);

    Car findById(Long userId);

    List<Car> search(String query);

    Car update(Car user);

    Long delete(Car object);
}
