package com.noirix.repository.impl;

import com.noirix.domain.Role;
import com.noirix.repository.RoleRepository;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private static final Logger log = Logger.getLogger(RoleRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RoleRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Role save(Role object) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Role findById(Long key) {
        return null;
    }

    @Override
    public Optional<Role> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public Role update(Role object) {
        return null;
    }

    @Override
    public Long delete(Role object) {
        return null;
    }

    @Override
    public List<Role> findUserRoles(Long userId) {
        return Collections.emptyList();
    }
}
