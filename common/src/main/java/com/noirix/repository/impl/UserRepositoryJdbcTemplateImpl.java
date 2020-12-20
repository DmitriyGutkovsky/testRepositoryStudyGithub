package com.noirix.repository.impl;

//import com.noirix.SpringContextTester;
import com.noirix.domain.Gender;
import com.noirix.domain.User;
import com.noirix.repository.UserColumns;
import com.noirix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Slf4j
// @RequiredArgsConstructor
@Repository
//@Primary
public class UserRepositoryJdbcTemplateImpl implements UserRepository {

  private static final Logger log = Logger.getLogger(UserRepositoryJdbcTemplateImpl.class);

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
    log.info("invoking search method()");
    log.info(query);

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
  public Optional<User> findByLogin(String login) {
    return Optional.of(jdbcTemplate.queryForObject("select * from m_users where login = ?", new Object[]{login}, this::getUserRowMapper));
  }

  @Override
  public User save(User user) {
    final String saveQuery =
            "insert into m_users (name, surname, birth_date, gender, created, changed, weight, login, password) "
                    + "values (:name, :surname, :birthDate, :gender, :created, :changed, :weight, :login, :password)";

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("name", user.getName());
    params.addValue("surname", user.getSurname());
    params.addValue("birthDate", user.getBirthDate());
    params.addValue("gender", user.getGender().name());
    params.addValue("created", user.getCreated());
    params.addValue("changed", user.getChanged());
    params.addValue("weight", user.getWeight());
    params.addValue("login", user.getLogin());
    params.addValue("password", user.getPassword());

    namedParameterJdbcTemplate.update(saveQuery,params,keyHolder, new String[]{"id"}); // column for primary key, param: new String[]{"id"}

    long value = Objects.requireNonNull(keyHolder.getKey()).longValue();

    return findById(value);
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
  public User update(User user) {
    final String updateQuery =
            "update m_users "
                    + "set " +
                    "name = :name, " +
                    "surname = :surname, " +
                    "birth_date = :birthDate, " +
                    "gender = :gender, " +
                    "created = :created, " +
                    "changed = :changed, " +
                    "weight = :weight, " +
                    "password = :password, " +
                    "login = :login " +
                    "where id = :userId";

    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("name", user.getName());
    mapSqlParameterSource.addValue("surname", user.getSurname());
    mapSqlParameterSource.addValue("birthDate", user.getBirthDate());
    mapSqlParameterSource.addValue("gender", user.getGender().name());
    mapSqlParameterSource.addValue("created", user.getCreated());
    mapSqlParameterSource.addValue("changed", user.getChanged());
    mapSqlParameterSource.addValue("weight", user.getWeight());
    mapSqlParameterSource.addValue("userId", user.getId());
    mapSqlParameterSource.addValue("login", user.getLogin());
    mapSqlParameterSource.addValue("password", user.getPassword());

    namedParameterJdbcTemplate.update(updateQuery,mapSqlParameterSource);

    return findById(user.getId());
  }

  @Override
  public Long delete(User object) {
    final String deleteQuery = "delete from m_users where id = :userId";
    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("userId", object.getId());
    long deletedId = object.getId();
    int deletedRow = namedParameterJdbcTemplate.update(deleteQuery, mapSqlParameterSource);
//    return (long) deletedRow; // returns number of deleted rows
    return deletedId; // returns deleted id
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
    user.setLogin(rs.getString(UserColumns.LOGIN));
    user.setPassword(rs.getString(UserColumns.PASSWORD));

    return user;
  }
}
