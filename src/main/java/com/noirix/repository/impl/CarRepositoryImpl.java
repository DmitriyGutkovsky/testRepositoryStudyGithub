package com.noirix.repository.impl;

import com.noirix.domain.Car;
import com.noirix.repository.CarRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarRepositoryImpl implements CarRepository {

  public static final String POSTGRES_DRIVER_NAME = "org.postgresql.Driver";
  public static final String DATABASE_URL = "jdbc:postgresql://localhost:";
  public static final int DATABASE_PORT = 5432;
  public static final String DATABASE_NAME = "/test_database";
  public static final String DATABASE_LOGIN = "postgres";
  public static final String DATABASE_PASSWORD = "root";

  private static final String ID = "id";
  private static final String MODEL = "model";
  private static final String CREATION_YEAR = "creation_year";
  private static final String USER_ID = "user_id";
  private static final String PRICE = "price";
  private static final String COLOR = "color";

  @Override
  public List<Car> search(String query) {
    return null;
  }

  @Override
  public Car save(Car object) {
    final String createLine =
        "insert into m_cars(model,creation_year,user_id, price,color) VALUES(?,?,?,?,?)";
    Connection connection;
    PreparedStatement preparedStatement;
    Long index;

    try {
      Class.forName(POSTGRES_DRIVER_NAME);
    } catch (ClassNotFoundException e) {
      System.err.println("JDBC Driver Cannot be loaded!");
      throw new RuntimeException("JDBC Driver Cannot be loaded!");
    }

    String jdbcURL = StringUtils.join(DATABASE_URL, DATABASE_PORT, DATABASE_NAME);

    try {
      connection = DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);
      preparedStatement = connection.prepareStatement(createLine);
      preparedStatement.setString(1, object.getModel());
      preparedStatement.setInt(2, object.getCreationYear());
      preparedStatement.setLong(3, object.getUser_id());
      preparedStatement.setDouble(4, object.getPrice());
      preparedStatement.setString(5, object.getColor());
      index = (long) preparedStatement.executeUpdate();

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      throw new RuntimeException("SQL Isses in save()!");
    }

    Car createdCar = findById(index);

    return createdCar;
  }

  @Override
  public List<Car> findAll() {
    final String findAllQuery = "select * from m_cars order by id";

    List<Car> result = new ArrayList<>();

    Connection connection;
    Statement statement;
    ResultSet rs;

    try {
      Class.forName(POSTGRES_DRIVER_NAME);
    } catch (ClassNotFoundException e) {
      System.err.println("JDBC Driver Cannot be loaded!");
      throw new RuntimeException("JDBC Driver Cannot be loaded!");
    }

    String jdbcURL = StringUtils.join(DATABASE_URL, DATABASE_PORT, DATABASE_NAME);

    try {
      connection = DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);
      statement = connection.createStatement();
      rs = statement.executeQuery(findAllQuery);

      while (rs.next()) {
        Car car = new Car();
        car.setId(rs.getLong(ID));
        car.setModel(rs.getString(MODEL));
        car.setCreationYear(rs.getInt(CREATION_YEAR));
        car.setUser_id(rs.getLong(USER_ID));
        car.setPrice(rs.getDouble(PRICE));
        car.setColor(rs.getString(COLOR));

        result.add(car);
      }

      return result;

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      throw new RuntimeException("SQL Isses in findAll()!");
    }
  }

  @Override
  public Car findById(Long key) {
    String findById = "select * from m_cars where id=" + key;

    Connection connection;
    Statement statement;
    ResultSet rs;
    Car car = new Car();

    try {
      Class.forName(POSTGRES_DRIVER_NAME);
    } catch (ClassNotFoundException e) {
      System.err.println("JDBC Driver Cannot be loaded!");
      throw new RuntimeException("JDBC Driver Cannot be loaded!");
    }

    String jdbcURL = StringUtils.join(DATABASE_URL, DATABASE_PORT, DATABASE_NAME);

    try {
      connection = DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);
      statement = connection.createStatement();
      rs = statement.executeQuery(findById);

      while (rs.next()) {
        car.setId(rs.getLong(ID));
        car.setModel(rs.getString(MODEL));
        car.setCreationYear(rs.getInt(CREATION_YEAR));
        car.setUser_id(rs.getLong(USER_ID));
        car.setPrice(rs.getDouble(PRICE));
        car.setColor(rs.getString(COLOR));
      }

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      throw new RuntimeException("SQL Isses in findById()!");
    }
    return car;
  }

  @Override
  public Optional<Car> findOne(Long key) {
    return Optional.empty();
  }

  @Override
  public Car update(Car object) {
    String findById = "select * from m_cars where id=" + object.getId();

    Connection connection;
    Statement statement;
    ResultSet rs;
    Car carForUpdate = new Car();

    try {
      Class.forName(POSTGRES_DRIVER_NAME);
    } catch (ClassNotFoundException e) {
      System.err.println("JDBC Driver Cannot be loaded!");
      throw new RuntimeException("JDBC Driver Cannot be loaded!");
    }

    String jdbcURL = StringUtils.join(DATABASE_URL, DATABASE_PORT, DATABASE_NAME);

    try {
      connection = DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);
      statement = connection.createStatement();

      rs = statement.executeQuery(findById);

      while (rs.next()) {
        carForUpdate.setId(rs.getLong(ID));
        if (!object.getId().equals(carForUpdate.getId())) {
          rs.updateRow();
        }
        carForUpdate.setModel(rs.getString(MODEL));
        carForUpdate.setCreationYear(rs.getInt(CREATION_YEAR));
        carForUpdate.setUser_id(rs.getLong(USER_ID));
        carForUpdate.setPrice(rs.getDouble(PRICE));
        carForUpdate.setColor(rs.getString(COLOR));
      }

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      throw new RuntimeException("SQL Isses in findById()!");
    }

    final String updateQuery = "update m_cars set model='Lada' where id =" + object.getId();
    Car carAfterUpdate = new Car();

    return null;
  }

  @Override
  public Long delete(Car car) {

    if (car == null) {
      System.out.println("Object is null, cannot be deleted");
      throw new RuntimeException("Object is null, cannot be deleted");
    }

    final String deleteQuery = "delete from m_cars where id=" + car.getId();
    Connection connection;
    Statement statement;
    ResultSet rs;

    try {
      Class.forName(POSTGRES_DRIVER_NAME);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    String jdbcURL = StringUtils.join(DATABASE_URL, DATABASE_PORT, DATABASE_NAME);

    try {
      connection = DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);
      statement = connection.createStatement();
      statement.executeUpdate(deleteQuery);

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      throw new RuntimeException("SQL Isses in delete() method!");
    }
    return car.getId();
  }
}
