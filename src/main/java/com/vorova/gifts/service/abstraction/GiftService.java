package com.vorova.gifts.service.abstraction;

import com.vorova.gifts.model.entity.Gift;

public interface GiftService {

    Long add(Gift gift);

    boolean remove(long id);

    boolean update(Gift gift);

}
