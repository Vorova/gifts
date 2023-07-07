package com.vorova.gifts.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter

public class FastOrderException extends RuntimeException{

    private final List<String> messages = new ArrayList<>();

    public FastOrderException(String message) {
        super(message);
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}
