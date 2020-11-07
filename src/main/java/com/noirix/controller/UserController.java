package com.noirix.controller;

import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users") // all methods mapping will start with "/users"
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    public static final String USER_PAGE = "users";
    public static final String USERS_LIST_ATTRIBUTE = "users";

    // will be called by URL: /users and RequestMethod.GET
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllUsers(){

        ModelAndView result = new ModelAndView();
        result.setViewName(USER_PAGE);
        result.addObject(USERS_LIST_ATTRIBUTE, userService.findAll());

        return  result;
    }
    // will be called by URL: /users/search and RequestMethod.GET
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam String query){

        ModelAndView result = new ModelAndView();
        result.setViewName(USER_PAGE);
        result.addObject(USERS_LIST_ATTRIBUTE, userService.search(query));

        return  result;
    }






}
