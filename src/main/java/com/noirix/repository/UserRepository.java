package com.noirix.repository;

import com.noirix.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<Long, User> {

    List<User> search(String query);

    Optional<User> findByLogin (String login);

}
