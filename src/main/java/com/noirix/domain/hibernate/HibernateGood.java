package com.noirix.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "m_goods")
public class HibernateGood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    @ManyToMany
    @JoinTable( name = "l_user_goods", // - linked table
                joinColumns =  @JoinColumn(name = "good_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnoreProperties("goods")
    private Set<HibernateUser> users = Collections.emptySet();
}
