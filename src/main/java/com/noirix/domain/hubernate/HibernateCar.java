package com.noirix.domain.hubernate;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "m_cars")
public class HibernateCar {

    @Id
    private Long id;

    @Column
    private String model;

    @Column
    private  int creationYear;

    @Column
    private Long userId;

    @Column
    private Double price;

    @Column
    private String color;

}
