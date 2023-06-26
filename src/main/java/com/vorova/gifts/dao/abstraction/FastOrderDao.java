package com.vorova.gifts.dao.abstraction;

import com.vorova.gifts.model.entity.FastOrder;

import java.util.Optional;

public interface FastOrderDao {

    Long add(FastOrder fastOrder);

    void update(FastOrder fastOrder);

    void remove(FastOrder fastOrder);

    Optional<FastOrder> getById(Long id);

}