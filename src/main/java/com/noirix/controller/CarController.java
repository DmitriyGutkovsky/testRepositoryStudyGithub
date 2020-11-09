package com.noirix.controller;

import com.noirix.controller.requests.CarCreateRequest;
import com.noirix.controller.requests.SearchCriteria;
import com.noirix.domain.Car;
import com.noirix.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

  public final CarService carService;

  public static final String CARS_PAGE = "cars";

  public static final String CARS_LIST_ATTRIBUTE = "cars";

  @GetMapping
  public ModelAndView getAllCars() {
    ModelAndView result = new ModelAndView();
    result.setViewName(CARS_PAGE);
    result.addObject(CARS_LIST_ATTRIBUTE, carService.findAll());
    return result;
  }

  @GetMapping(value = "/{carId}")
  public ModelAndView getById(@PathVariable Long carId) {
    ModelAndView result = new ModelAndView();
    result.setViewName(CARS_PAGE);
    result.addObject(CARS_LIST_ATTRIBUTE, Collections.singletonList(carService.findById(carId)));
    return result;
  }

  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public ModelAndView searchCar(
      @RequestParam("query") String queryParam, @RequestParam("limit") Long limit) {

    ModelAndView result = new ModelAndView();
    result.setViewName(CARS_PAGE);
    result.addObject(
        CARS_LIST_ATTRIBUTE,
        carService.search(queryParam)
                .stream()
                .limit(limit)
                .collect(Collectors.toList()));

    return result;
  }

  @GetMapping(value = "/search/car")
  public ModelAndView searchCar(@ModelAttribute SearchCriteria searchCriteria) {

    ModelAndView result = new ModelAndView();
    result.setViewName(CARS_PAGE);
    result.addObject(
        CARS_LIST_ATTRIBUTE,
        carService.search(searchCriteria.getQuery())
                .stream()
                .limit(searchCriteria.getLimit())
                .collect(Collectors.toList()));

    return result;
  }

  @GetMapping("/create")
  public ModelAndView getCarCreateRequest(){
    ModelAndView result = new ModelAndView();

    result.setViewName("createcar");
    result.addObject("carCreateRequest", new CarCreateRequest());

    return  result;
  }

  @PostMapping
  public ModelAndView createCar(@ModelAttribute CarCreateRequest carCreateRequest){
    Car car = new Car();

    car.setColor(carCreateRequest.getColor());
    car.setPrice(carCreateRequest.getPrice());
    car.setUserId(carCreateRequest.getUserId());
    car.setCreationYear(carCreateRequest.getCreationYear());
    car.setModel(carCreateRequest.getModel());

    carService.save(car);

    ModelAndView result = new ModelAndView();
    result.setViewName(CARS_PAGE);
    result.addObject(CARS_LIST_ATTRIBUTE, carService.findAll());

    return result;
  }
}
