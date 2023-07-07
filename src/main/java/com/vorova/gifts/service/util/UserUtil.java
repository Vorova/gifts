package com.vorova.gifts.service.util;

import com.vorova.gifts.exception.UserException;
import com.vorova.gifts.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    public static void checkCorrectly(User user) {
        UserException exception = new UserException();
        if (user.getUsername() == null || user.getUsername().equals("")) {
            exception.addMessage("Login не может быть пустым");
        }
        if (user.getPassword() == null
                || user.getPassword().equals("")
                || user.getPassword().length() < 5) {
            exception.addMessage("password должен содержать от 5 символов");
        }
        if (user.getAuthorities() == null || user.getAuthorities().isEmpty()) {
            exception.addMessage("Пользователь не может быть без прав");
        }

        if (!exception.getMessages().isEmpty()) {
            throw exception;
        }
    }

}
