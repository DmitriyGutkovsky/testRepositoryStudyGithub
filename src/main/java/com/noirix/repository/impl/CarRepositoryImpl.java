package com.noirix.repository.impl;

import com.noirix.domain.Car;
import com.noirix.exception.EntityNotFoundException;
import com.noirix.repository.CarRepository;
import com.noirix.util.DatabasePropertiesReader;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.noirix.util.DatabasePropertiesReader.*;

public class CarRepositoryImpl implements CarRepository {

  private static final String ID = "id";
  private static final String MODEL = "model";
  private static final String CREATION_YEAR = "creation_year";
  private static final String USER_ID = "user_id";
  private static final String PRICE = "price";
  private static final String COLOR = "color";

  public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

  private Car parseResultSet(ResultSet rs) throws SQLException {
    Car car = new Car();
    car.setId(rs.getLong(ID));
    car.setModel(rs.getString(MODEL));
    car.setCreationYear(rs.getInt(CREATION_YEAR));
    car.setUser_id(rs.getLong(USER_ID));
    car.setPrice(rs.getDouble(PRICE));
    car.setColor(rs.getString(COLOR));
    return car;
  }

  @Override
  public List<Car> search(String query) {
    return null;
  }

  @Override
  public Car save(Car car) {
    final String createLine =
        "insert into m_cars(model,creation_year,user_id, price,color) VALUES(?,?,?,?,?)";
    Connection connection;
    PreparedStatement preparedStatement;
    Long index;

    try {
      Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
    } catch (ClassNotFoundException e) {
      System.err.println("JDBC Driver Cannot be loaded!");
      throw new RuntimeException("JDBC Driver Cannot be loaded!");
    }

    try {
      connection =
          DriverManager.getConnection(
              reader.getProperty(DATABASE_URL),
              reader.getProperty(DATABASE_LOGIN),
              reader.getProperty(DATABASE_PASSWORD));
      preparedStatement = connection.prepareStatement(createLine);

      PreparedStatement lastInsertId =
          connection.prepareStatement("SELECT currval('m_cars_id_seq') as last_insert_id;");

      preparedStatement.setString(1, car.getModel());
      preparedStatement.setInt(2, car.getCreationYear());
      preparedStatement.setLong(3, car.getUser_id());
      preparedStatement.setDouble(4, car.getPrice());
      preparedStatement.setString(5, car.getColor());

      preparedStatement.executeUpdate();

      ResultSet lastIdResultSet = lastInsertId.executeQuery();
      long insertedId;
      if (lastIdResultSet.next()) {
        insertedId = lastIdResultSet.getLong("last_insert_id");
      } else {
        throw new RuntimeException("We cannot read sequence last value during User creation!");
      }

      return findById(insertedId);

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      throw new RuntimeException("SQL Isses in save()!");
    }
  }

  @Override
  public List<Car> findAll() {
    final String findAllQuery = "select * from m_cars order by id";

    List<Car> result = new ArrayList<>();

    Connection connection;
    Statement statement;
    ResultSet rs;

    try {
      Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
    } catch (ClassNotFoundException e) {
      System.err.println("JDBC Driver Cannot be loaded!");
      throw new RuntimeException("JDBC Driver Cannot be loaded!");
    }

    try {
      connection =
          DriverManager.getConnection(
              reader.getProperty(DATABASE_URL),
              reader.getProperty(DATABASE_LOGIN),
              reader.getProperty(DATABASE_PASSWORD));
      statement = connection.createStatement();
      rs = statement.executeQuery(findAllQuery);

      while (rs.next()) {
        result.add(parseResultSet(rs));
      }
      return result;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      throw new RuntimeException("SQL Isses in findAll()!");
    }
  }

  @Override
  public Car findById(Long key) {
    String findByIdQuery = "select * from m_cars where id = ?";

    Connection connection;
    PreparedStatement statement;
    ResultSet rs;

    try {
      Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
    } catch (ClassNotFoundException e) {
      System.err.println("JDBC Driver Cannot be loaded!");
      throw new RuntimeException("JDBC Driver Cannot be loaded!");
    }

    try {
      connection =
          DriverManager.getConnection(
              reader.getProperty(DATABASE_URL),
              reader.getProperty(DATABASE_LOGIN),
              reader.getProperty(DATABASE_PASSWORD));
      statement = connection.prepareStatement(findByIdQuery);
      statement.setLong(1, key);
      rs = statement.executeQuery();

      if (rs.next()) {
        return parseResultSet(rs);
      } else {
        throw new EntityNotFoundException("Car with ID:" + key + " not found");
      }

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      throw new RuntimeException("SQL Isses in findById()!");
    }
  }

  @Override
  public Optional<Car> findOne(Long key) {
    return Optional.of(findById(key));
  }

  @Override
  public Car update(Car car) {
    final String updateQuery =
        "update m_cars set "
                + "model = ?, "
                + "creation_year = ?, "
                + "user_id = ?, price = ?, "
                + "color = ? "
                + "where id = ?";
    Connection connection;
    PreparedStatement preparedStatement;

    try {
      Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
    } catch (ClassNotFoundException e) {
      System.err.println("JDBC Driver Cannot be loaded!");
      throw new RuntimeException("JDBC Driver Cannot be loaded!");
    }

    try {
      connection =
          DriverManager.getConnection(
              reader.getProperty(DATABASE_URL),
              reader.getProperty(DATABASE_LOGIN),
              reader.getProperty(DATABASE_PASSWORD));
      preparedStatement = connection.prepareStatement(updateQuery);
      preparedStatement.setString(1, car.getModel());
      preparedStatement.setInt(2, car.getCreationYear());
      preparedStatement.setLong(3, car.getUser_id());
      preparedStatement.setDouble(4, car.getPrice());
      preparedStatement.setString(5, car.getColor());
      preparedStatement.setLong(6, car.getId());

      preparedStatement.executeUpdate();

      return findById(car.getId());

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      throw new RuntimeException("SQL Isses in update()!");
    }
  }

  @Override
  public Long delete(Car car) {

    final String deleteQuery = "delete from m_cars where id = ?";

    Connection connection;
    PreparedStatement statement;

    try {
      Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
    } catch (ClassNotFoundException e) {
      System.err.println("JDBC Driver Cannot be loaded!");
      throw new RuntimeException("JDBC Driver Cannot be loaded!");
    }

    try {
      connection =
          DriverManager.getConnection(
              reader.getProperty(DATABASE_URL),
              reader.getProperty(DATABASE_LOGIN),
              reader.getProperty(DATABASE_PASSWORD));
      statement = connection.prepareStatement(deleteQuery);
      statement.setLong(1, car.getId());

      int deletedRows = statement.executeUpdate();
      return (long) deletedRows;

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      throw new RuntimeException("SQL Isses in delete() method!");
    }
  }
}
