package com.noirix.domain.hubernate;

import com.noirix.domain.Gender;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "m_users")
public class HibernateUser {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NOT_SELECTED;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column
    private Float weight;

    @Column
    private String login;

    @Column
    private String password;

}
