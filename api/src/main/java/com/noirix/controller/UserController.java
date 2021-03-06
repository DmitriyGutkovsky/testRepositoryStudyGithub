package com.noirix.controller;

import com.noirix.controller.requests.SearchCriteria;
import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.controller.requests.UserUpdateRequest;
import com.noirix.domain.User;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users") // all methods mapping will start with "/users"
@RequiredArgsConstructor
public class UserController {

  public final UserService userService;

  public static final String USER_PAGE = "users";
  public static final String USERS_LIST_ATTRIBUTE = "users";

  // will be called by URL: /users and RequestMethod.GET: http://localhost:8080/users
  //    @RequestMapping(method = RequestMethod.GET)
  @GetMapping
  public ModelAndView getAllUsers() {

    ModelAndView result = new ModelAndView();
    result.setViewName(USER_PAGE);
    result.addObject(USERS_LIST_ATTRIBUTE, userService.findAll());

    return result;
  }
  // will be called by URL: /users/search and RequestMethod.GET
  //    @RequestMapping(value = "/search", method = RequestMethod.GET)
  //    public ModelAndView search(@RequestParam ("query") String queryParam,
  //                               @RequestParam ("limit") Long limit ){
  @GetMapping(value = "/search")
  public ModelAndView search(@ModelAttribute SearchCriteria criteria) {

    ModelAndView result = new ModelAndView();
    result.setViewName(USER_PAGE);
    result.addObject(
        USERS_LIST_ATTRIBUTE,
        userService.search(criteria.getQuery()).stream()
            .limit(criteria.getLimit())
            .collect(Collectors.toList()));

    return result;
  }

  //  @GetMapping(value = "/search")
  //  public ModelAndView search(ModelMap modelMap) {
  //
  //    ModelAndView result = new ModelAndView();
  //
  //    String resultQuery = StringUtils.isNotBlank((String) modelMap.get("query")) ?
  //            (String)modelMap.get("query") : "Viacheslau";
  //
  //    Long resultLimit = (Long) modelMap.get("imit") != null ? (Long) modelMap.get("imit") : 10;
  //    result.setViewName(USER_PAGE);
  //    result.addObject(
  //            USERS_LIST_ATTRIBUTE,
  //            userService.search(resultQuery).stream()
  //                    .limit(resultLimit)
  //                    .collect(Collectors.toList()));
  //
  //    return result;
  //  }

  //      /users/1
  @GetMapping(value = "/{id}")
  public ModelAndView search(@PathVariable("id") Long userId) {
    ModelAndView result = new ModelAndView();
    result.setViewName(USER_PAGE);
    result.addObject(USERS_LIST_ATTRIBUTE, Collections.singletonList(userService.findById(userId)));

    return result;
  }

  //    http://localhost:8080/users/create
  @GetMapping("/create")
  public ModelAndView getUserCreateRequest(){

    ModelAndView result = new ModelAndView();

    result.setViewName("createuser");
    result.addObject("userCreateRequest", new UserCreateRequest());

    return  result;
  }

  @PostMapping
  public ModelAndView createUser(@ModelAttribute UserCreateRequest userCreateRequest) {

    User user = new User();

    user.setGender(userCreateRequest.getGender());
    user.setName(userCreateRequest.getName());
    user.setSurname(userCreateRequest.getSurname());
    user.setBirthDate(userCreateRequest.getBirthDate());
    user.setWeight(userCreateRequest.getWeight());

    userService.save(user);

    ModelAndView result = new ModelAndView();
    result.setViewName(USER_PAGE);
    result.addObject(USERS_LIST_ATTRIBUTE, userService.findAll());

    return result;
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    sdf.setLenient(true);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
  }

  //    http://localhost:8080/users/delete?id=40
  @GetMapping("/delete")
  public ModelAndView deleteUser(@RequestParam("id") Long userId){
    User deletedUser = userService.findById(userId);
    userService.delete(deletedUser);

    ModelAndView result = new ModelAndView();

    result.setViewName(USER_PAGE);
    result.addObject(USERS_LIST_ATTRIBUTE, userService.findAll());

    return result;
  }

  //  http://localhost:8080/users/update
  @GetMapping("/update")
  public ModelAndView updateUserRequest(){
    ModelAndView result = new ModelAndView();
    result.setViewName("updateuser");
    result.addObject("userUpdateRequest", new UserUpdateRequest());
    return result;
  }


  @PostMapping("/update")
  public ModelAndView updateUser(@ModelAttribute UserUpdateRequest userUpdateRequest){

    User updatedUser = userService.findById(userUpdateRequest.getId());

    updatedUser.setName(userUpdateRequest.getName());
    updatedUser.setSurname(userUpdateRequest.getSurname());
    updatedUser.setBirthDate(userUpdateRequest.getBirthDate());
    updatedUser.setGender(userUpdateRequest.getGender());
    updatedUser.setWeight(userUpdateRequest.getWeight());
    updatedUser.setChanged(userUpdateRequest.getChanged());

    userService.update(updatedUser);

    ModelAndView result = new ModelAndView();
    result.setViewName(USER_PAGE);
    result.addObject(USERS_LIST_ATTRIBUTE, userService.findAll());

    return result;
  }
}
