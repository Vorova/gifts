package com.vorova.gifts.service.imp;

import com.vorova.gifts.dao.abstraction.GiftDao;
import com.vorova.gifts.exception.GiftException;
import com.vorova.gifts.model.entity.Gift;
import com.vorova.gifts.model.entity.Image;
import com.vorova.gifts.model.dto.FilterSearchDto;
import com.vorova.gifts.service.abstraction.GiftService;
import com.vorova.gifts.service.util.GiftUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        GiftUtil.checkCorrectly(gift);
        if (gift.getImages() != null && !gift.getImages().isEmpty()) {
            for (Image image : gift.getImages()) {
                image.setGift(gift);
            }
        }
        return giftDao.add(gift);
    }

    @Override
    @Transactional
    public void remove(long id) {
        Optional<Gift> optionalGift = giftDao.getById(id);
        if(optionalGift.isEmpty()) {
            throw new GiftException("Подарка с таким id не существует");
        } else {
            giftDao.remove(optionalGift.get());
        }
    }

    @Override
    @Transactional
    public void update(Gift gift) {
        GiftUtil.checkCorrectly(gift);
        if (gift.getImages() != null && !gift.getImages().isEmpty()) {
            for (Image image : gift.getImages()) {
                image.setGift(gift);
            }
        }
        giftDao.update(gift);
    }

    @Override
    @Transactional(readOnly = true)
    public Gift getById(Long id) {
        return giftDao.getById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gift> getByFilter(FilterSearchDto filter) {
        return giftDao.getByFilter(filter);
    }

}
