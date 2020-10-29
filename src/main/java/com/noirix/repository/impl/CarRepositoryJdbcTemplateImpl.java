package com.noirix.repository.impl;

import com.noirix.domain.Car;
import com.noirix.repository.CarRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

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
        final String saveQuery = "insert into m_cars (model, creation_year, user_id, price, color) "
                        + "values (:model, :creationYear, :userId, :price, :color)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("model", car.getModel());
        params.addValue("creationYear", car.getCreationYear());
        params.addValue("userId", car.getUserId());
        params.addValue("price", car.getPrice());
        params.addValue("color", car.getColor());

        namedParameterJdbcTemplate.update(saveQuery, params,keyHolder, new String[]{"id"});

        long insertedId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(insertedId);
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Car findById(Long key) {
        return null;
    }

    @Override
    public Optional<Car> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public Car update(Car object) {
        return null;
    }

    @Override
    public Long delete(Car object) {
        return null;
    }

}
