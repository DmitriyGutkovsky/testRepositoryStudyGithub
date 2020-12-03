package com.noirix.repository;

import com.noirix.domain.hibernate.HibernateUser;

import java.util.List;
import java.util.Optional;

public interface HibernateUserRepository extends CrudRepository<Long, HibernateUser> {
    List<HibernateUser> search(String query);

    Optional<HibernateUser> findByLogin(String login);
}
