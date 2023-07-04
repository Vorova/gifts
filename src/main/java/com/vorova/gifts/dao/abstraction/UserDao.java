package com.vorova.gifts.dao.abstraction;

import com.vorova.gifts.model.entity.User;
import java.util.Optional;

public interface UserDao {

    Optional<User> getByUsername(String username);

}
