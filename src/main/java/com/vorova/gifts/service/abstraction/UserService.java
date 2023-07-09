package com.vorova.gifts.service.abstraction;

import com.vorova.gifts.model.entity.User;

public interface UserService {

    Long add(User user);

    void update(User user);

    void remove(Long id);
}
