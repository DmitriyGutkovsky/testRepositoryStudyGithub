package com.noirix.controller;

import com.noirix.controller.requests.SearchCriteria;
import com.noirix.domain.User;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UserRestController {

    public final UserService userService;

    //http://localhost:8080/rest/users
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
//        return ResponseEntity.ok(userService.findAll());
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    //http://localhost:8080/rest/users/5
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    // http://localhost:8080/rest/users/search?query=Viachaslau
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<User> userSearch(@ModelAttribute SearchCriteria searchCriteria){
        return userService.search(searchCriteria.getQuery());
    }

}
