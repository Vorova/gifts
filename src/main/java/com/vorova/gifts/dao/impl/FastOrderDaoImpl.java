package com.vorova.gifts.dao.impl;

import com.vorova.gifts.dao.abstraction.FastOrderDao;
import com.vorova.gifts.exception.FastOrderException;
import com.vorova.gifts.model.entity.FastOrder;
import com.vorova.gifts.service.util.FastOrderUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FastOrderDaoImpl implements FastOrderDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public FastOrderDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Long add(FastOrder fastOrder) {
        entityManager.persist(fastOrder);
        return fastOrder.getId();
    }

    @Override
    public void update(FastOrder fastOrder) {
        FastOrder persistedFastOrder = getById(fastOrder.getId()).orElse(null);
        if (persistedFastOrder == null) {
            throw new FastOrderException("Заказа с таким id не существует");
        }

        if (persistedFastOrder.getGift() != fastOrder.getGift() ||
                persistedFastOrder.getBox() != fastOrder.getBox()) {
            double costs = FastOrderUtil.getAllCost(fastOrder);
            double price = FastOrderUtil.getAllPrice(fastOrder);
            persistedFastOrder.setCosts(costs);
            persistedFastOrder.setPrice(price);
            persistedFastOrder.setRevenue(price - costs);
        }

        persistedFastOrder.setGift(fastOrder.getGift());
        persistedFastOrder.setBox(fastOrder.getBox());
        persistedFastOrder.setAddress(fastOrder.getAddress());
        persistedFastOrder.setTel(fastOrder.getTel());
        persistedFastOrder.setEmail(fastOrder.getEmail());
        persistedFastOrder.setName(fastOrder.getName());

        FastOrderUtil.checkCorrectly(persistedFastOrder);

        entityManager.flush();
    }

    @Override
    public void remove(FastOrder fastOrder) {
        entityManager.remove(fastOrder);
    }

    @Override
    public Optional<FastOrder> getById(Long id) {
        try {
            return Optional.of(entityManager.find(FastOrder.class, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
