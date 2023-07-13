package com.vorova.gifts.dao.abstraction;

import com.vorova.gifts.model.entity.FastOrder;
import com.vorova.gifts.model.enums.FastOrderStatus;

import java.util.Optional;

public interface FastOrderDao {

    Long add(FastOrder fastOrder);

    void update(FastOrder fastOrder);

    void remove(FastOrder fastOrder);

    Optional<FastOrder> getById(Long id);

    void updateStatus(Long id, FastOrderStatus fastOrderStatus);
}