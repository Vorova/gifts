package com.vorova.gifts.dao.impl;

import com.vorova.gifts.dao.abstraction.GiftDao;
import com.vorova.gifts.model.entity.Gift;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GiftDaoImpl implements GiftDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public GiftDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Long add(Gift gift) {
        entityManager.persist(gift);
        return gift.getId();
    }

    @Override
    public boolean remove(long id) {
        // todo
        return false;
    }

    @Override
    public boolean update(Gift gift) {
        // todo
        return false;
    }
}
