package com.vorova.gifts.model.dto;

import lombok.Data;

@Data
public class AppResponse {

    private String message;

    AppResponse(String message) {
        this.message = message;
    }

    public static AppResponse response(String message){
        return new AppResponse(message);
    }

}
