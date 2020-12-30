package com.noirix.converter;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.SystemRoles;
import com.noirix.domain.hibernate.HibernateRole;
import com.noirix.domain.hibernate.HibernateUser;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class UserCreateRequestConverter extends EntityConverter<UserCreateRequest, HibernateUser> {

    @Override
    public HibernateUser convert(UserCreateRequest request) {

        HibernateUser user = new HibernateUser();
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));

        HibernateRole hibernateRole = new HibernateRole();
        hibernateRole.setRoleName(SystemRoles.ROLE_USER);
        hibernateRole.setUser(user);
        user.setRole(hibernateRole);

        return doConvert(user, request);
    }
}
