package com.vorova.gifts.controller;

import com.vorova.gifts.model.dto.AppErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class AbstractController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppErrorDto> handlerException(Exception e){
        List<String> errorsMessages = new ArrayList<>();
        errorsMessages.add(e.getMessage());
        AppErrorDto appError = new AppErrorDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                errorsMessages
        );
        return new ResponseEntity<>(appError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
