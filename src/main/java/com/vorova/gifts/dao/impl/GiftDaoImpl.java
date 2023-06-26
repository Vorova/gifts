package com.vorova.gifts.dao.impl;

import com.vorova.gifts.dao.abstraction.GiftDao;
import com.vorova.gifts.exception.GiftException;
import com.vorova.gifts.model.entity.Gift;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GiftDaoImpl implements GiftDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public GiftDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Long add(Gift gift) throws GiftException {
        entityManager.persist(gift);
        entityManager.flush();
        return gift.getId();
    }

    @Override
    public void remove(Gift gift) {
        entityManager.remove(gift);
    }

    @Override
    public void update(Gift gift) {
        try {
            Gift gift1 = entityManager.find(Gift.class, gift.getId());
            gift.setDateAdded(gift1.getDateAdded());
            gift.setType(gift1.getType());
        } catch (Exception e) {
            throw new GiftException("Подарка с таким id не существует");
        }
        entityManager.merge(gift);
    }

    @Override
    public Optional<Gift> getById(long id) {
        try {
            return Optional.of(entityManager.find(Gift.class, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
