package com.vorova.gifts.dao.impl;

import com.vorova.gifts.dao.abstraction.UserDao;
import com.vorova.gifts.exception.UserException;
import com.vorova.gifts.model.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> getByUsername(String username) {
        String hql = """
                FROM User user WHERE user.username = :username
                """;
        try {
            return Optional.of(
                    entityManager.createQuery(hql, User.class)
                            .setParameter("username", username)
                            .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Long add(User user) {
        try {
            entityManager.persist(user);
        } catch (Exception e) {
            UserException exception = new UserException();
            exception.addMessage("Не удалось сохранить пользователя");
            exception.addMessage(e.getMessage());
            throw exception;
        }
        return user.getId();
    }

    @Override
    public void update(User user) {
        try {
            entityManager.merge(user);
        } catch (Exception e) {
            UserException exception = new UserException();
            exception.addMessage("Не удалось обновить пользователя");
            exception.addMessage(e.getMessage());
            throw exception;
        }
    }
}
