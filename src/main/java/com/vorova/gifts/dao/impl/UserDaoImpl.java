package com.vorova.gifts.dao.impl;

import com.vorova.gifts.dao.abstraction.UserDao;
import com.vorova.gifts.exception.UserException;
import com.vorova.gifts.model.entity.Action;
import com.vorova.gifts.model.entity.User;
import com.vorova.gifts.model.enums.ActionType;
import com.vorova.gifts.service.abstraction.ActionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    private final ActionService actionService;

    @Autowired
    public UserDaoImpl(EntityManager entityManager, ActionService actionService) {
        this.entityManager = entityManager;
        this.actionService = actionService;
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
            Action action = new Action();
            // todo add user to action

            action.setUser(null);
            action.setType(ActionType.CREATE_USER);
            action.setDescription("create new user");
            actionService.add(action);
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

    @Override
    public void remove(Long id) {
        try {
            var user = new User();
            user.setId(id);
            entityManager.remove(user);
        } catch (Exception e) {
            var exception = new UserException();
            exception.addMessage("Не удалось удалить пользователя");
            exception.addMessage(e.getMessage());
            throw exception;
        }
    }

}