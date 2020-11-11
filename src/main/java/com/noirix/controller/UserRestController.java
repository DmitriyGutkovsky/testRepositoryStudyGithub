package com.noirix.controller;

import com.noirix.controller.requests.SearchCriteria;
import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.controller.requests.UserUpdateRequest;
import com.noirix.domain.User;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

    //  http://localhost:8080/rest/users
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User savingUser(@RequestBody UserCreateRequest userCreateRequest){

        User user = new User();

        user.setGender(userCreateRequest.getGender());
        user.setName(userCreateRequest.getName());
        user.setSurname(userCreateRequest.getSurname());
        user.setBirthDate(userCreateRequest.getBirthDate());
        user.setWeight(userCreateRequest.getWeight());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(new Timestamp(System.currentTimeMillis()));

        return userService.save(user);
    }

    //    http://localhost:8080/rest/users/47
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable Long id,
                           @RequestBody UserCreateRequest userCreateRequest){

        User user = userService.findById(id);

        user.setGender(userCreateRequest.getGender());
        user.setName(userCreateRequest.getName());
        user.setSurname(userCreateRequest.getSurname());
        user.setBirthDate(userCreateRequest.getBirthDate());
        user.setWeight(userCreateRequest.getWeight());
        user.setChanged(new Timestamp(System.currentTimeMillis()));

        return userService.update(user);
    }

    // http://localhost:8080/rest/users ,   userId send in request body
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {

        User user = userService.findById(userUpdateRequest.getId());

        //converters
        user.setGender(userUpdateRequest.getGender());
        user.setName(userUpdateRequest.getName());
        user.setSurname(userUpdateRequest.getSurname());
        user.setBirthDate(userUpdateRequest.getBirthDate());
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        user.setWeight(userUpdateRequest.getWeight());
        return userService.update(user);
    }

}
