package com.noirix.service.impl;

import com.noirix.domain.Car;
import com.noirix.repository.CarRepository;
import com.noirix.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car findById(Long carId) {
        return carRepository.findById(carId);
    }

    @Override
    public List<Car> search(String query) {
        return carRepository.search(query);
    }

    @Override
    public Car update(Car car) {
        return carRepository.update(car);
    }

    @Override
    public Long delete(Car car) {
        return carRepository.delete(car);
    }
}
