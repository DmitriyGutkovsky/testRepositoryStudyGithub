package com.noirix.repository;

import com.noirix.domain.Role;

import java.util.List;

public interface RoleRepository extends CrudRepository<Long, Role>{
    List<Role> findUserRoles(Long userId);
}
