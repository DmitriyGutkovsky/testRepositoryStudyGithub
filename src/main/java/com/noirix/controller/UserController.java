package com.noirix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
public class UserController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView getHelloPage(){
        return new ModelAndView("goodbye", Collections.singletonMap(
                "someMessage", "Spring MVC is working!"));
    }
}
