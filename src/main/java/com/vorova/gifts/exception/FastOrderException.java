package com.vorova.gifts.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FastOrderException extends AbstractException {

    public FastOrderException(String message) {
        super(message);
    }

}
