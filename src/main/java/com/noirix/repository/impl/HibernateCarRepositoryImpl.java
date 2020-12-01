package com.noirix.repository.impl;

import com.noirix.repository.HibernateCarRepository;
import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@Log4j2
public class HibernateCarRepositoryImpl implements HibernateCarRepository {

    private final SessionFactory sessionFactory;

    public HibernateCarRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public HibernateCarRepository save(HibernateCarRepository object) {
        return null;
    }

    @Override
    public List<HibernateCarRepository> findAll() {
        return null;
    }

    @Override
    public HibernateCarRepository findById(Long key) {
        return null;
    }

    @Override
    public Optional<HibernateCarRepository> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public HibernateCarRepository update(HibernateCarRepository object) {
        return null;
    }

    @Override
    public Long delete(HibernateCarRepository object) {
        return null;
    }
}
