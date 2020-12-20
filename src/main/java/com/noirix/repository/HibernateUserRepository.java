package com.noirix.repository;

import com.noirix.controller.requests.SearchCriteria;
import com.noirix.domain.hibernate.HibernateUser;

import java.util.List;
import java.util.Optional;

public interface HibernateUserRepository extends CrudRepository<Long, HibernateUser> {
    List<HibernateUser> search(String query);

    Optional<HibernateUser> findByLogin(String login);

    Object testHql();

    List<HibernateUser> testCriteriaApi(SearchCriteria criteria);

    List<HibernateUser> testCriteriaApiTask(SearchCriteria criteria);

    List<HibernateUser> testCache();
}
