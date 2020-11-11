package com.noirix.controller.requests;

import com.noirix.domain.Gender;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class UserUpdateRequest extends UserCreateRequest {

    private Long id;

//    private String name;

//    private String surname;

//    private Date birthDate;

//    private Gender gender = Gender.NOT_SELECTED;

//    private Float weight;

    private Timestamp changed = new Timestamp(System.currentTimeMillis());

}
