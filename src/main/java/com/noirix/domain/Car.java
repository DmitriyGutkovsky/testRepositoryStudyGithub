package com.noirix.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Car {
    private Long id;
    private String model;
    private  int creationYear;
    private Long user_id;
    private Double price;
    private String color;

    public Car() {
    }

    public Car(Long id, String model, int creationYear, Long user_id, Double price, String color) {
        this.id = id;
        this.model = model;
        this.creationYear = creationYear;
        this.user_id = user_id;
        this.price = price;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public Long getUser_id() {
        return user_id;
    }

    public Double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return creationYear == car.creationYear &&
                Objects.equals(id, car.id) &&
                Objects.equals(model, car.model) &&
                Objects.equals(user_id, car.user_id) &&
                Objects.equals(price, car.price) &&
                Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, creationYear, user_id, price, color);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
