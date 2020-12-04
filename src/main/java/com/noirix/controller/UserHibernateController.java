package com.noirix.controller;

import com.noirix.controller.requests.SearchCriteria;
import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.controller.requests.UserUpdateRequest;
import com.noirix.domain.hibernate.HibernateRole;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.HibernateUserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/users/hibernate")
public class UserHibernateController {

    public final HibernateUserRepository hibernateUserRepository;

    @GetMapping
    public ResponseEntity<List<HibernateUser>> findAllHibernateUser() {
       return new ResponseEntity<>(hibernateUserRepository.findAll(), HttpStatus.OK);
    }


    // http://localhost:8080/rest/users/5
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HibernateUser findUserById(@PathVariable Long id) {
        return hibernateUserRepository.findById(id);
    }

    // http://localhost:8080/rest/users/search?query=Viachaslau
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<HibernateUser> userSearch(@ModelAttribute SearchCriteria searchCriteria) {
        return hibernateUserRepository.search(searchCriteria.getQuery());
    }

    //  http://localhost:8080/rest/users
    @ApiOperation(value = "Endpoint for creation users")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string" ),
//            @ApiImplicitParam(name = "Query", defaultValue = "query", required = false, paramType = "query", dataType = "string")
//    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HibernateUser savingUser(@RequestBody UserCreateRequest userCreateRequest) {

        HibernateUser user = new HibernateUser();

        user.setGender(userCreateRequest.getGender());
        user.setName(userCreateRequest.getName());
        user.setSurname(userCreateRequest.getSurname());
        user.setBirthDate(userCreateRequest.getBirthDate());
        user.setWeight(userCreateRequest.getWeight());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        user.setLogin(userCreateRequest.getLogin());
        user.setPassword(userCreateRequest.getPassword());

        user.setRoles(Collections.singleton(new HibernateRole("ROLE_ADMIN", user)));
//        user.setRole(new HibernateRole("ROLE_ADMIN", user));

        return hibernateUserRepository.save(user);
    }

    //    http://localhost:8080/rest/users/47
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HibernateUser updateUser(@PathVariable Long id, @RequestBody UserCreateRequest userCreateRequest) {

        HibernateUser user = hibernateUserRepository.findById(id);

        user.setGender(userCreateRequest.getGender());
        user.setName(userCreateRequest.getName());
        user.setSurname(userCreateRequest.getSurname());
        user.setBirthDate(userCreateRequest.getBirthDate());
        user.setWeight(userCreateRequest.getWeight());
        user.setChanged(new Timestamp(System.currentTimeMillis()));

        user.setRoles(Collections.singleton(new HibernateRole("ROLE_ADMIN", user)));
//        user.setRole(new HibernateRole("ROLE_ADMIN", user));

        return hibernateUserRepository.update(user);
    }

    // http://localhost:8080/rest/users ,   userId send in request body
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public HibernateUser updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {

        HibernateUser user = hibernateUserRepository.findById(userUpdateRequest.getId());

        // converters
        user.setGender(userUpdateRequest.getGender());
        user.setName(userUpdateRequest.getName());
        user.setSurname(userUpdateRequest.getSurname());
        user.setBirthDate(userUpdateRequest.getBirthDate());
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        user.setWeight(userUpdateRequest.getWeight());
        return hibernateUserRepository.update(user);
    }

    //  http://localhost:8080/rest/users/47
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<HibernateUser> deleteUser(@PathVariable Long id){
        HibernateUser deletedUser= hibernateUserRepository.findById(id);
        hibernateUserRepository.delete(deletedUser);
        return hibernateUserRepository.findAll();
    }

}
