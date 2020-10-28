package com.noirix.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.util.Date;

// @Data
@Setter
@Getter
// @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private Long id;

  private String name;

  private String surname;

  private Date birthDate;

  private Gender gender = Gender.NOT_SELECTED;

  private Timestamp created = new Timestamp(System.currentTimeMillis());

  private Timestamp changed = new Timestamp(System.currentTimeMillis());

  private Float weight;

  //only Spring annotations
//  @Autowired
//  @Qualifier("car1")

  //standard JSR-330 . Java-annotations  - some as @Autowire and @Qualifier
//  @Inject
//  @Named("car1")
  private Car usercar;

  // Injection via constructor
  @Autowired
// (if there are more than one constructor in class, it is necessary to specify
//    or do not use other constructors with autowire, because we don't know how bean will be created)
  public User(Car usercar){
    this.usercar = usercar;
  }

//  public User(Long id, String name, String surname) {
//    this.id = id;
//    this.name = name;
//    this.surname = surname;
//  }

//  @Autowired
//  public void setUsercar(Car usercar) {
//    this.usercar = usercar;
//  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
