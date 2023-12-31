package com.vorova.gifts.service.impl;

import com.vorova.gifts.dao.abstraction.FastOrderDao;
import com.vorova.gifts.dao.abstraction.GiftDao;
import com.vorova.gifts.exception.FastOrderException;
import com.vorova.gifts.model.entity.FastOrder;
import com.vorova.gifts.model.enums.FastOrderStatus;
import com.vorova.gifts.service.abstraction.FastOrderService;
import com.vorova.gifts.service.util.FastOrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FastOrderServiceImpl implements FastOrderService {

    private final FastOrderDao fastOrderDao;
    private final GiftDao giftDao;

    @Autowired
    public FastOrderServiceImpl(FastOrderDao fastOrderDao, GiftDao giftDao) {
        this.fastOrderDao = fastOrderDao;
        this.giftDao = giftDao;
    }

    @Override
    @Transactional
    public Long add(FastOrder fastOrder) {
        fastOrder.setGift(giftDao.getById(fastOrder.getGift().getId()).orElse(null));
        fastOrder.setBox(giftDao.getById(fastOrder.getBox().getId()).orElse(null));
        double costs = FastOrderUtil.getAllCost(fastOrder);
        double price = FastOrderUtil.getAllPrice(fastOrder);

        fastOrder.setCosts(costs);
        fastOrder.setPrice(price);
        fastOrder.setDate(LocalDateTime.now());
        fastOrder.setRevenue(price - costs);
        fastOrder.setStatus(FastOrderStatus.NEW);

        FastOrderUtil.checkCorrectly(fastOrder);
        return fastOrderDao.add(fastOrder);
    }

    @Override
    @Transactional
    public void update(FastOrder fastOrder) {
        fastOrder.setGift(giftDao.getById(fastOrder.getGift().getId()).orElse(null));
        fastOrder.setBox(giftDao.getById(fastOrder.getBox().getId()).orElse(null));
        FastOrderUtil.checkCorrectly(fastOrder);
        fastOrderDao.update(fastOrder);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Optional<FastOrder> fastOrder = fastOrderDao.getById(id);
        if (fastOrder.isEmpty()) {
            FastOrderException exception = new FastOrderException();
            exception.addMessage("Не удалось удалить. Такого заказа не существует");
            throw exception;
        }
        if (fastOrder.get().getStatus().equals(FastOrderStatus.NEW)) {
            fastOrderDao.remove(fastOrder.get());
        }
        FastOrderException exception = new FastOrderException();
        exception.addMessage("Нельзя удалить оплаченный заказ");
        throw exception;
    }

    @Override
    @Transactional
    public void updateStatus(Long id, FastOrderStatus fastOrderStatus) {
        fastOrderDao.updateStatus(id, fastOrderStatus);
    }

}
