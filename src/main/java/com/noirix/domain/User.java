package com.noirix.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

//@Data
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    private String name;

    private String surname;

    private Date birthDate;

    private Gender gender = Gender.NOT_SELECTED;

    private Timestamp created;

    private Timestamp changed;

    private Float weight;

//  @Override
//  public String toString() {
//    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
//  }

}
