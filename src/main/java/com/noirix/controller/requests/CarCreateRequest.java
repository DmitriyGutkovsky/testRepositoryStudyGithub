package com.noirix.controller.requests;

import lombok.Data;

@Data
public class CarCreateRequest {

//    private Long id;

    private String model;

    private int creationYear;

    private long userId;

    private double price;

    private String color;

}
