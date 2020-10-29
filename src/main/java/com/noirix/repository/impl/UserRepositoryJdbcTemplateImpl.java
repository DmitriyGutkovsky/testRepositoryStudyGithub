package com.noirix.repository.impl;

import com.noirix.domain.Gender;
import com.noirix.domain.User;
import com.noirix.repository.UserColumns;
import com.noirix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// @RequiredArgsConstructor
@Repository
@Primary
public class UserRepositoryJdbcTemplateImpl implements UserRepository {

  //    private final JdbcTemplate jdbcTemplate;
  //    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private JdbcTemplate jdbcTemplate;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public UserRepositoryJdbcTemplateImpl(
      JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public List<User> search(String query) {
    // first way using  NamedParameterJdbcTemplate
//    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//    mapSqlParameterSource.addValue("name", query);
//    return Collections.singletonList(namedParameterJdbcTemplate.queryForObject(
//            "select * from m_users where name = :name", mapSqlParameterSource, this::getUserRowMapper));

    // second way using JdbcTemplate
//    return Collections.singletonList(jdbcTemplate.queryForObject(
//            "select * from m_users where name = ?", new Object[]{query}, this::getUserRowMapper));

    // third way
    return jdbcTemplate.query(
            "select * from m_users where name like ?", new Object[]{query}, this::getUserRowMapper);


  }

  @Override
  public User save(User object) {
    return null;
  }

  @Override
  public List<User> findAll() {
    return jdbcTemplate.query("select * from m_users",this::getUserRowMapper);
//    return Collections.singletonList(new User());
  }

  @Override
  public User findById(Long key) {

    // first way using  NamedParameterJdbcTemplate

//    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//    mapSqlParameterSource.addValue("userId", key);

//    return namedParameterJdbcTemplate.queryForObject(
//            "select * from m_users where id = :userId", mapSqlParameterSource, this::getUserRowMapper);

    // second way using JdbcTemplate
    return   jdbcTemplate.queryForObject(
            "select * from m_users where id = ?", new Object[]{key}, this::getUserRowMapper);
  }

  @Override
  public Optional<User> findOne(Long key) {
    return Optional.empty();
  }

  @Override
  public User update(User object) {
    return null;
  }

  @Override
  public Long delete(User object) {
    return null;
  }

  private User getUserRowMapper(ResultSet rs, int i) throws SQLException{
    User user = new User();
    user.setId(rs.getLong(UserColumns.ID));
    user.setName(rs.getString(UserColumns.NAME));
    user.setSurname(rs.getString(UserColumns.SURNAME));
    user.setBirthDate(rs.getDate(UserColumns.BIRTH_DATE));
    user.setGender(Gender.valueOf(rs.getString(UserColumns.GENDER)));
    user.setCreated(rs.getTimestamp(UserColumns.CREATED));
    user.setChanged(rs.getTimestamp(UserColumns.CHANGED));
    user.setWeight(rs.getFloat(UserColumns.WEIGHT));

    return user;
  }
}
