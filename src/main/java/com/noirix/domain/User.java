package com.noirix.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

  public User(Long id, String name, String surname) {
    this.id = id;
    this.name = name;
    this.surname = surname;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
