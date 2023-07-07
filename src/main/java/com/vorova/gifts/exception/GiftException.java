package com.vorova.gifts.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GiftException extends RuntimeException {
    private final List<String> messages = new ArrayList<>();

    public GiftException(String message) {
        super(message);
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}
