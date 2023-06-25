package com.vorova.gifts.dao.abstraction;

import com.vorova.gifts.model.entity.Gift;

import java.util.Optional;

public interface GiftDao {

    Long add(Gift gift);

    void remove(Gift gift);

    void update(Gift gift);

    Optional<Gift> getById(long id);

}
