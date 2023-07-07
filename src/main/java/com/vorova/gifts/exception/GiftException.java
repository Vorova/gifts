package com.vorova.gifts.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GiftException extends AbstractException {
    public GiftException(String message) {
        super(message);
    }
}
