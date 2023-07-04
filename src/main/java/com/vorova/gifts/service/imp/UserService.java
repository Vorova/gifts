package com.vorova.gifts.service.imp;

import com.vorova.gifts.dao.abstraction.UserDao;
import com.vorova.gifts.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.getByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Пользователя с таким именем не существует");
        }
        return user.get();
    }
}