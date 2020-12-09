package com.noirix.repository.impl;

import com.noirix.controller.requests.SearchCriteria;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.domain.hibernate.HibernateUser_;
import com.noirix.repository.HibernateUserRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
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

//              "select u.id, role.roleName, u.weight from HibernateUser u left join u.role as role " +
//              "where role.roleName = 'ROLE_ADMIN' ";



              "select u.id, role.roleName, u.weight from HibernateUser u left join u.role as role " +
//                            " " +
//              "where role.roleName = 'ROLE_ADMIN' " +
              "where role.roleName = com.noirix.domain.SystemRoles.ROLE_ADMIN " +
              "and u.weight > (select avg(u.weight) from HibernateUser u) " +
              "and u.id in(55, 56, 52) " +
              "and u.name like '%o%' " +
//                            "having u.weight > avg(u.weight) " +
              "";




      return session.createQuery(hqlQuery).list();
    }

  }

  @Override
  public List<HibernateUser> testCriteriaApi(SearchCriteria criteria) {

    EntityManager entityManager = sessionFactory.createEntityManager();

    //1. Get Builder for Criteria object
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<HibernateUser> query = cb.createQuery(HibernateUser.class); //here select, where, orderBy, having
    Root<HibernateUser> root = query.from(HibernateUser.class); //here params  select * from m_users -> mapping

    /*type of future params in prepared statement*/
    ParameterExpression<String> param = cb.parameter(String.class);
    ParameterExpression<Long> userSearchParam = cb.parameter(Long.class);

    /*Provide access to fields in class that mapped to columns*/
    Expression<Long> id = root.get(HibernateUser_.id);
    Expression<String> name = root.get(HibernateUser_.name);
    Expression<String> surname = root.get(HibernateUser_.surname);

    /*SQL Query customizing*/
    query.select(root)
            .distinct(true)
            .where(
                    cb.or(
                            cb.like(name, param),  //userName like param
                            cb.like(surname, param)  //userSurname like param
                    ),
                    cb.and(
                            cb.gt(root.get(HibernateUser_.id), userSearchParam), //>0
                            cb.not(id.in(40L, 50L)) //in (40,50)
                    )
//                        ,
//                        cb.between(
//                                root.get(TestUser_.birthDate),
//                                new Timestamp(new Date().getTime()),
//                                new Timestamp(new Date().getTime())
//                        )
            )
            .orderBy(cb.asc(root.get(HibernateUser_.id)));

    TypedQuery<HibernateUser> resultQuery = entityManager.createQuery(query); //prepared statement on hql
    resultQuery.setParameter(param, StringUtils.join("%", criteria.getQuery(), "%"));
    resultQuery.setParameter(userSearchParam, criteria.getUserLowerId());
    return resultQuery.getResultList();
  }

}
