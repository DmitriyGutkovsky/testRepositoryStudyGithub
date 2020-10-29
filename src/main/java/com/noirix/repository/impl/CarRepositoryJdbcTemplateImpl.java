package com.noirix.repository.impl;

import com.noirix.domain.Car;
import com.noirix.repository.CarColumns;
import com.noirix.repository.CarRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Primary
public class CarRepositoryJdbcTemplateImpl implements CarRepository {

  private JdbcTemplate jdbcTemplate;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public CarRepositoryJdbcTemplateImpl(
      JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public List<Car> search(String query) {
    return null;
  }

  @Override
  public Car save(Car car) {
    final String saveQuery =
        "insert into m_cars (model, creation_year, user_id, price, color) "
            + "values (:model, :creationYear, :userId, :price, :color)";

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("model", car.getModel());
    params.addValue("creationYear", car.getCreationYear());
    params.addValue("userId", car.getUserId());
    params.addValue("price", car.getPrice());
    params.addValue("color", car.getColor());

    namedParameterJdbcTemplate.update(saveQuery, params, keyHolder, new String[] {"id"});

    long insertedId = Objects.requireNonNull(keyHolder.getKey()).longValue();

    return findById(insertedId);
  }

  @Override
  public List<Car> findAll() {
    return jdbcTemplate.query("select * from m_cars", this::getCarRowMapper);
  }

  @Override
  public Car findById(Long key) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("carId", key);
    return namedParameterJdbcTemplate.queryForObject(
        "select * from m_cars where id = :carId", param, this::getCarRowMapper);
  }

  @Override
  public Optional<Car> findOne(Long key) {
    return Optional.empty();
  }

  @Override
  public Car update(Car car) {
    final String updateQuery =
        "update m_cars "
            + "set "
            + "model = :model, "
            + "creation_year = :creationYear, "
            + "user_id = :userId, "
            + "price = :price, "
            + "color = :color "
            + "where id = :carId";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("model", car.getModel());
    params.addValue("creationYear", car.getCreationYear());
    params.addValue("userId", car.getUserId());
    params.addValue("price", car.getPrice());
    params.addValue("color", car.getColor());
    params.addValue("carId", car.getId());

    namedParameterJdbcTemplate.update(updateQuery, params);

    return findById(car.getId());
  }

  @Override
  public Long delete(Car car) {
    final String deleteQuery = "delete from m_cars where id = :carId";
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("carId", car.getId());
    long deletedId = car.getId();
    int deletedRow = namedParameterJdbcTemplate.update(deleteQuery, param);
    return (long) deletedRow; // returns number of deleted rows
    //        return deletedId; // returns deleted id
  }

  private Car getCarRowMapper(ResultSet rs, int i) throws SQLException {
    Car car = new Car();

    car.setId(rs.getLong(CarColumns.ID));
    car.setModel(rs.getString(CarColumns.MODEL));
    car.setCreationYear(rs.getInt(CarColumns.CREATION_YEAR));
    car.setUserId(rs.getLong(CarColumns.USER_ID));
    car.setPrice(rs.getDouble(CarColumns.PRICE));
    car.setColor(rs.getString(CarColumns.COLOR));

    return car;
  }
}
