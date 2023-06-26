package com.vorova.gifts.service.abstraction;

import com.vorova.gifts.model.entity.FastOrder;

public interface FastOrderService {

    Long add(FastOrder fastOrder);

    void update(FastOrder fastOrder);

    void remove(Long id);

}
