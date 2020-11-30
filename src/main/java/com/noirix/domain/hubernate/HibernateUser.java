package com.noirix.domain.hubernate;

import com.noirix.domain.Gender;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "m_users")
public class HibernateUser {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;


//    @Column(name = "birth_date")
//    @Temporal(TemporalType.DATE)
//    private Date birthDate;

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
