package com.vorova.gifts.dao.impl;

import com.vorova.gifts.dao.abstraction.UserDao;
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
}
