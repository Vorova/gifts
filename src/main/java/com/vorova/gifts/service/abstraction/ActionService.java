package com.vorova.gifts.service.abstraction;

import com.vorova.gifts.model.enums.ActionType;

public interface ActionService {

    void add(ActionType actionType, Long subject);

}
