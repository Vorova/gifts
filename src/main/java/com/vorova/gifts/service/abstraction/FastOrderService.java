package com.vorova.gifts.service.abstraction;

import com.vorova.gifts.model.entity.FastOrder;
import com.vorova.gifts.model.enums.FastOrderStatus;

public interface FastOrderService {

    Long add(FastOrder fastOrder);

    void update(FastOrder fastOrder);

    void remove(Long id);

    void updateStatus(Long id, FastOrderStatus fastOrderStatus);

}
