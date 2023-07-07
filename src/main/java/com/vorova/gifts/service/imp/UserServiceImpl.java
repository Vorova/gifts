package com.vorova.gifts.service.imp;

import com.vorova.gifts.dao.abstraction.UserDao;
import com.vorova.gifts.exception.UserException;
import com.vorova.gifts.model.entity.User;
import com.vorova.gifts.service.abstraction.UserService;
import com.vorova.gifts.service.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Такого пользователя не существует")
        );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }

    @Override
    @Transactional
    public Long add(User user) {
        UserUtil.checkCorrectly(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.add(user);
    }

    @Override
    public void update(User user) {
        UserUtil.checkCorrectly(user);
        if (user.getId() == null) {
            UserException exception = new UserException();
            exception.addMessage("Нельзя обновлять нулевого пользователя");
        }
        userDao.update(user);
    }
}