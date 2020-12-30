package com.noirix.converter;

import com.noirix.controller.requests.UserUpdateRequest;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.exception.EntityNotFoundException;
import com.noirix.repository.impl.UserSpringDataRepository;
import org.springframework.stereotype.Component;

@Component
public class UserEditRequestConverter extends EntityConverter<UserUpdateRequest, HibernateUser> {

    private UserSpringDataRepository userRepository;

    public UserEditRequestConverter(UserSpringDataRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public HibernateUser convert(UserUpdateRequest request) {

        HibernateUser hibernateUser = userRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
        return doConvert(hibernateUser, request);
    }
}
