package com.vorova.gifts.service.abstraction;

import com.vorova.gifts.model.entity.Gift;
import com.vorova.gifts.model.dto.FilterSearchDto;

import java.util.List;

public interface GiftService {

    Long add(Gift gift);

    void remove(long id);

    void update(Gift gift);

    Gift getById(Long id);

    List<Gift> getByFilter(FilterSearchDto filterSearchDto);
}
