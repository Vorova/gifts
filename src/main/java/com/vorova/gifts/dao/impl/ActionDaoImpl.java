package com.vorova.gifts.dao.impl;

import com.vorova.gifts.dao.abstraction.ActionDao;
import com.vorova.gifts.model.entity.Action;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ActionDaoImpl implements ActionDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public ActionDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Action action) {
        try {
            entityManager.persist(action);
        } catch (Exception exception) {
            log.warn("не удалось создать лог");
        }
    }
}
