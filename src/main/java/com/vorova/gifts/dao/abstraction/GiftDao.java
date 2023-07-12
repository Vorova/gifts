package com.vorova.gifts.dao.abstraction;

import com.vorova.gifts.model.entity.Gift;
import com.vorova.gifts.model.dto.FilterSearchDto;

import java.util.List;
import java.util.Optional;

public interface GiftDao {

    Long add(Gift gift);

    Long remove(Gift gift);

    void update(Gift gift);

    Optional<Gift> getById(long id);

    List<Gift> getByFilter(FilterSearchDto filter);
}
