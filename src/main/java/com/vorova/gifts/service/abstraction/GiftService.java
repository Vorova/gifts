package com.vorova.gifts.service.abstraction;

import com.vorova.gifts.model.entity.Gift;

import java.util.Optional;

public interface GiftService {

    Long add(Gift gift);

    void remove(long id);

    void update(Gift gift);

}
