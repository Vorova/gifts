package com.vorova.gifts.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AppErrorDto {

    private int status;
    private List<String> messages;
    private Date timestamp;

    public AppErrorDto(int status, List<String> messages) {
        this.status = status;
        this.messages = messages;
        this.timestamp = new Date();
    }

}