package com.vorova.gifts.service.imp;

import com.vorova.gifts.dao.abstraction.GiftDao;
import com.vorova.gifts.model.entity.Gift;
import com.vorova.gifts.service.abstraction.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GiftServiceImp implements GiftService {

    private final GiftDao giftDao;

    @Autowired
    public GiftServiceImp(GiftDao giftDao) {
        this.giftDao = giftDao;
    }

    @Override
    @Transactional
    public Long add(Gift gift) {
        // todo проверка на корректность данного подарка
        return giftDao.add(gift);
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
