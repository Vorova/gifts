package com.vorova.gifts.controller;

import com.vorova.gifts.exception.UserException;
import com.vorova.gifts.mapper.UserMapper;
import com.vorova.gifts.model.dto.AppErrorDto;
import com.vorova.gifts.model.dto.UserDto;
import com.vorova.gifts.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/user")
public class UserAdminController extends AbstractController{

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserAdminController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody UserDto userDto) {
        userService.add(userMapper.toUser(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь успешно создан");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDto userDto) {
        userService.update(userMapper.toUser(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь успешно обновлен");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        // удаление пользователя
        return null;
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<AppErrorDto> handlerException(UserException e) {
        AppErrorDto appErrorDto = new AppErrorDto(
                HttpStatus.NOT_FOUND.value(),
                e.getMessages()
        );
        return new ResponseEntity<>(appErrorDto, HttpStatus.BAD_REQUEST);
    }

}
