package com.noirix.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.noirix.domain.SystemRoles;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "m_roles")
public class HibernateRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private SystemRoles roleName;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonBackReference
//    private HibernateUser user;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private HibernateUser user;

    public HibernateRole(Long id, SystemRoles roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public HibernateRole() {
    }

    public HibernateRole(SystemRoles roleName, HibernateUser user) {
        this.roleName = roleName;
        this.user = user;
    }

    public HibernateRole(SystemRoles roleName) {
        this.roleName = roleName;
    }
}
