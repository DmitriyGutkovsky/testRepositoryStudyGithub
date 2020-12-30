package com.noirix.converter;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.Credentials;
import com.noirix.domain.hibernate.HibernateUser;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class EntityConverter<S, T> implements Converter<S, T> {

  protected HibernateUser doConvert(HibernateUser user, UserCreateRequest request) {

    user.setName(request.getName());
    user.setSurname(request.getSurname());
    user.setBirthDate(request.getBirthDate());

    Credentials credentials = new Credentials();
    credentials.setLogin(request.getLogin());
    credentials.setPassword(request.getPassword());

    user.setCredentials(credentials);
    user.setChanged(new Timestamp(new Date().getTime()));
    user.setGender(request.getGender());
    user.setWeight(request.getWeight());

    return user;
  }
}
