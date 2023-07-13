package com.vorova.gifts.controller.admin;

import com.vorova.gifts.controller.AbstractController;
import com.vorova.gifts.exception.UserException;
import com.vorova.gifts.mapper.UserMapper;
import com.vorova.gifts.model.dto.AppErrorDto;
import com.vorova.gifts.model.dto.AppResponse;
import com.vorova.gifts.model.dto.UserDto;
import com.vorova.gifts.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/user")
public class UserAdminController extends AbstractController {

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
        return ResponseEntity
                .status(201)
                .contentType(MediaType.APPLICATION_JSON)
                .body(AppResponse.response("Пользователь успешно создан"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDto userDto) {
        userService.update(userMapper.toUser(userDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(AppResponse.response("Изменения сохранены"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.remove(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(AppResponse.response("Пользователь удалён"));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<AppErrorDto> handlerException(UserException e) {
        AppErrorDto appErrorDto = new AppErrorDto(
            HttpStatus.BAD_REQUEST.value(),
            e.getMessages()
        );
        return new ResponseEntity<>(appErrorDto, HttpStatus.BAD_REQUEST);
    }

}