package com.noirix.controller.requests;

import com.noirix.domain.Gender;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class UserUpdateRequest extends UserCreateRequest {

    private Long id;

    private Timestamp changed = new Timestamp(System.currentTimeMillis());

}
