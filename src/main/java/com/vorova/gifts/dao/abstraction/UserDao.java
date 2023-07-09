package com.vorova.gifts.dao.abstraction;

import com.vorova.gifts.model.entity.User;
import java.util.Optional;

public interface UserDao {

    Optional<User> getByUsername(String username);

    Long add(User user);

    void update(User user);

    void remove(Long id);
}
