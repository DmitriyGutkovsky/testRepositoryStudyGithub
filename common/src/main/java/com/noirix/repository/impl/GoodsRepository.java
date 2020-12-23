package com.noirix.repository.impl;

import com.noirix.domain.hibernate.HibernateGood;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<HibernateGood, Long> {

    @Cacheable("goods")
    @Query(value = "select g from HibernateGood g")
    List<HibernateGood> findAllWithCache();
}
