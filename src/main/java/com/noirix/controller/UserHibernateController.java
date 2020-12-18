package com.noirix.controller;

import com.noirix.controller.requests.SearchCriteria;
import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.controller.requests.UserUpdateRequest;
import com.noirix.domain.Credentials;
import com.noirix.domain.Gender;
import com.noirix.domain.SystemRoles;
import com.noirix.domain.hibernate.HibernateRole;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.HibernateUserRepository;
import com.noirix.repository.impl.UserSpringDataRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/users/hibernate")
public class UserHibernateController {

  public final HibernateUserRepository hibernateUserRepository;
  public final UserSpringDataRepository userSpringDataRepository;

  @GetMapping
  public ResponseEntity<Object> findAllHibernateUser() {
    Object all = hibernateUserRepository.testHql();
    //    public ResponseEntity<List<HibernateUser>> findAllHibernateUser() {
    //        List<HibernateUser> all = hibernateUserRepository.findAll();

    return new ResponseEntity<>(all, HttpStatus.OK);
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
  //            @ApiImplicitParam(name = "Auth-Token", defaultValue = "token", required = true,
  // paramType = "header", dataType = "string" ),
  //            @ApiImplicitParam(name = "Query", defaultValue = "query", required = false,
  // paramType = "query", dataType = "string")
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
//    user.setLogin(userCreateRequest.getLogin());
//    user.setPassword(userCreateRequest.getPassword());
    user.setCredentials(new Credentials(userCreateRequest.getLogin(),
            userCreateRequest.getPassword()));

    //        user.setRoles(Collections.singleton(new HibernateRole("ROLE_ADMIN", user)));
    //        user.setRole(new HibernateRole("ROLE_ADMIN", user));
    user.setRole(new HibernateRole(SystemRoles.ROLE_ADMIN, user));

    return hibernateUserRepository.save(user);
  }

  //    http://localhost:8080/rest/users/47
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public HibernateUser updateUser(
      @PathVariable Long id, @RequestBody UserCreateRequest userCreateRequest) {

    HibernateUser user = hibernateUserRepository.findById(id);

    user.setGender(userCreateRequest.getGender());
//    user.setName(userCreateRequest.getName());
//    user.setSurname(userCreateRequest.getSurname());
    user.setCredentials(new Credentials(userCreateRequest.getLogin(),
            userCreateRequest.getPassword()));
    user.setBirthDate(userCreateRequest.getBirthDate());
    user.setWeight(userCreateRequest.getWeight());
    user.setChanged(new Timestamp(System.currentTimeMillis()));

    //        user.setRoles(Collections.singleton(new HibernateRole("ROLE_ADMIN", user)));
    //        user.setRole(new HibernateRole("ROLE_ADMIN", user));
    user.setRole(new HibernateRole(SystemRoles.ROLE_ADMIN, user));

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
    user.setCredentials(new Credentials(userUpdateRequest.getLogin(),
            userUpdateRequest.getPassword()));
    user.setBirthDate(userUpdateRequest.getBirthDate());
    user.setChanged(new Timestamp(System.currentTimeMillis()));
    user.setWeight(userUpdateRequest.getWeight());
    return hibernateUserRepository.update(user);
  }

  //  http://localhost:8080/rest/users/47
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public List<HibernateUser> deleteUser(@PathVariable Long id) {
    HibernateUser deletedUser = hibernateUserRepository.findById(id);
    hibernateUserRepository.delete(deletedUser);
    return hibernateUserRepository.findAll();
  }

  @GetMapping("/creteriaapi")
  public ResponseEntity<Object> testCriteriaAPI(@ModelAttribute SearchCriteria criteria) {
    Object all = hibernateUserRepository.testCriteriaApi(criteria);
    return new ResponseEntity<>(all, HttpStatus.OK);
  }

  @GetMapping("/creteriaapi/task")
  public ResponseEntity<Object> testCriteriaAPITask(@ModelAttribute SearchCriteria criteria) {
    Object all = hibernateUserRepository.testCriteriaApiTask(criteria);
    return new ResponseEntity<>(all, HttpStatus.OK);
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "page",
        dataType = "integer",
        paramType = "query",
        value = "Results page you want to retrieve (0..N)"),
    @ApiImplicitParam(
        name = "size",
        dataType = "integer",
        paramType = "query",
        value = "Number of records per page."),
    @ApiImplicitParam(
        name = "sort",
        allowMultiple = true,
        dataType = "string",
        paramType = "query",
        value =
            "Sorting criteria in the format: property(,asc|desc). "
                + "Default sort order is ascending. "
                + "Multiple sort criteria are supported.")
  })
  @GetMapping("/spring-data/all")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Page<HibernateUser>> getUsersSpringData(@ApiIgnore Pageable pageable) {
    return new ResponseEntity<>(userSpringDataRepository.findAll(pageable), HttpStatus.OK);
  }

  @GetMapping("/spring-data/")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<HibernateUser>> testSpringData() {
    return new ResponseEntity<>(userSpringDataRepository.findAll(), HttpStatus.OK);
  }

  @GetMapping("/spring-data/function")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<HibernateUser> testSpringDatafunction() {
    return new ResponseEntity<>(userSpringDataRepository
            .findUserWithFunctionalCall(Gender.MALE.name(), "Slava", "TestUpdate", "loginSlava"),
            HttpStatus.OK);
  }

  /*TODO: transaction manager configuration required*/
  @Modifying
  @PostMapping("/spring-data/modifying/test")
  public ResponseEntity<Object> testCreatingLinkRow() {
    return new ResponseEntity<>(userSpringDataRepository.createSomeRow(4L, 3L),
            HttpStatus.CREATED);
  }

}
