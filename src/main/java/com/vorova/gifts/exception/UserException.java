package com.vorova.gifts.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserException extends AbstractException {
    public UserException(String message) {
        super(message);
    }
}
