package com.vorova.gifts.dao.impl;

import com.vorova.gifts.dao.abstraction.FastOrderDao;
import com.vorova.gifts.exception.FastOrderException;
import com.vorova.gifts.model.entity.FastOrder;
import com.vorova.gifts.model.enums.FastOrderStatus;
import com.vorova.gifts.service.util.FastOrderUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
public class FastOrderDaoImpl implements FastOrderDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public FastOrderDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Long add(FastOrder fastOrder) {
        try {
            entityManager.persist(fastOrder);
            return fastOrder.getId();
        } catch (Exception e) {
            var exception = new FastOrderException();
            exception.addMessage("Не удалось создать новый подарок");
            exception.addMessage(e.getMessage());
            throw exception;
        }
    }

    @Override
    public void update(FastOrder fastOrder) {
        FastOrder persistedFastOrder = getById(fastOrder.getId()).orElse(null);
        if (persistedFastOrder == null) {
            var exception = new FastOrderException();
            exception.addMessage("Заказа с таким id не существует");
            throw exception;
        }

        if (!persistedFastOrder.getStatus().equals(FastOrderStatus.NEW)) {
            var exception = new FastOrderException();
            exception.addMessage("Возможно изменение только не оплаченных заказов");
            throw exception;
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
        try {
            entityManager.remove(fastOrder);
        } catch (Exception e) {
            var exception = new FastOrderException();
            exception.addMessage("Удаление не удалось");
            throw exception;
        }
    }

    @Override
    public Optional<FastOrder> getById(Long id) {
        try {
            return Optional.of(entityManager.find(FastOrder.class, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateStatus(Long id, FastOrderStatus fastOrderStatus) {
        String hql = """
            UPDATE FastOrder as order
            SET order.status = :newStatus
            WHERE order.id = :id
            """;
        try {
            entityManager
                    .createQuery(hql)
                    .setParameter("newStatus", fastOrderStatus)
                    .setParameter("id", id);
        } catch (Exception e) {
            var exception = new FastOrderException();
            exception.addMessage("Не удалось обновить статус заказа");
            log.info("Не удалось обновить статус заказа");
            throw exception;
        }
    }
}
