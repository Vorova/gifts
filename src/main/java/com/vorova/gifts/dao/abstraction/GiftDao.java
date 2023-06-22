package com.vorova.gifts.dao.abstraction;

import com.vorova.gifts.model.entity.Gift;

public interface GiftDao {

    Long add(Gift gift);

    boolean remove(long id);

    boolean update(Gift gift);

}
