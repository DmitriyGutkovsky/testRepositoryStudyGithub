package com.noirix.controller;

import com.noirix.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    public final CarService carService;

    public static final String CARS_PAGE = "cars";

    public static final String CARS_LIST_ATTRIBUTE = "cars";

    @GetMapping
    public ModelAndView getAllCars(){
        ModelAndView result = new ModelAndView();
        result.setViewName(CARS_PAGE);
        result.addObject(CARS_LIST_ATTRIBUTE, carService.findAll());
        return result;
    }

    @GetMapping(value = "/{carId}")
    public ModelAndView getById(@PathVariable Long carId){
        ModelAndView result = new ModelAndView();
        result.setViewName(CARS_PAGE);
        result.addObject(CARS_LIST_ATTRIBUTE, Collections.singletonList(carService.findById(carId)));
        return result;
    }


}
