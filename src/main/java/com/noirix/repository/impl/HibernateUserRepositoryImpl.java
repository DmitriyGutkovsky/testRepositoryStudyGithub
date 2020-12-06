package com.noirix.repository.impl;

import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.HibernateUserRepository;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Primary
@Log4j2
public class HibernateUserRepositoryImpl implements HibernateUserRepository {

  private final SessionFactory sessionFactory;

  public HibernateUserRepositoryImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public HibernateUser save(HibernateUser object) {
    try (Session session = sessionFactory.openSession()) {
      session.saveOrUpdate(object);
      return object;
    }
  }

  @Override
  public List<HibernateUser> findAll() {

    //    try (Session currentSession = sessionFactory.getCurrentSession()) { // для каждого
    // отдельного запроса желательно создавать отдельную сессию
//    try (Session session = sessionFactory.openSession()) {
//      return Collections.singletonList(
//          session.find(HibernateUser.class, 5l)); // find HibernateUser with primary key = 6
//    }

    // HQL
    /*1. Change table name to mapped Entity: m_users -> HibernateUser u*/
    /*2. Change table column names to mapped Entity fields:
                  select * from m_users
                  select u from HibernateUser u

                  select id, name, birth_date from m_users
                  select u.id, u.name, u.birthDate from HibernateUser u
                  */
    try (Session session = sessionFactory.openSession()){

      String hqlQuery =
              "select u from HibernateUser u"
//              "from HibernateUser"
              ;

      return session.createQuery(hqlQuery, HibernateUser.class).list();
    }
  }

  @Override
  public HibernateUser findById(Long key) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(HibernateUser.class, key); // find HibernateUser with primary key = 6
    }
  }

  @Override
  public Optional<HibernateUser> findOne(Long key) {
    return Optional.of(findById(key));
  }

  @Override
  public HibernateUser update(HibernateUser object) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.saveOrUpdate(object);
      transaction.commit();
      return object;
    }
  }

  @Override
  public Long delete(HibernateUser object) {
    try (Session session = sessionFactory.openSession()) {
      session.delete(object);
      return object.getId();
    }
  }

  @Override
  public List<HibernateUser> search(String query) {
    return null;
  }

  @Override
  public Optional<HibernateUser> findByLogin(String login) {
    return Optional.empty();
  }

  @Override
  public Object testHql() {

    try (Session session = sessionFactory.openSession()){

      String hqlQuery =
//              "select u from HibernateUser u"
//              "from HibernateUser"
//              "select u.id, role.roleName from HibernateUser u left join u.role as role" ;// correct query for OneToOne relationship

              "select u.id, role.roleName, u.weight from HibernateUser u left join u.role as role " +
              "where role.roleName = 'ROLE_ADMIN' ";



//              "select u.id, role.roleName, u.weight from HibernateUser u left join u.role as role " +
////                            " " +
//              "where role.roleName = com.noirix.domain.SystemRoles.ROLE_ADMIN " +
//              "and u.weight > (select avg(u.weight) from HibernateUser u) " +
//              "and u.id in(45, 46, 42) " +
//              "and u.name like '%o%' " +
////                            "having u.weight > avg(u.weight) " +
//              "";




      return session.createQuery(hqlQuery).list();
    }

  }
}
