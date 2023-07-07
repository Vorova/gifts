package com.vorova.gifts.controller;

import com.vorova.gifts.exception.FastOrderException;
import com.vorova.gifts.mapper.FastOrderMapper;
import com.vorova.gifts.model.dto.AppErrorDto;
import com.vorova.gifts.model.dto.CreateFastOrderDto;
import com.vorova.gifts.model.dto.FastOrderDto;
import com.vorova.gifts.service.abstraction.FastOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/fast-order")
public class FastOrderController extends AbstractController{

    private final FastOrderService fastService;
    private final FastOrderMapper fastOrderMapper;

    @Autowired
    public FastOrderController(FastOrderService fastService, FastOrderMapper fastOrderMapper) {
        this.fastService = fastService;
        this.fastOrderMapper = fastOrderMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CreateFastOrderDto fastOrderDto) {
        fastService.add(fastOrderMapper.toFastOrder(fastOrderDto));
        return ResponseEntity.status(HttpStatus.CREATED).body("Заказ успешно создан");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody FastOrderDto fastOrderDto) {
        fastService.update(fastOrderMapper.toFastOrder(fastOrderDto));
        return ResponseEntity.ok().body("Заказ успешно обновлен");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        fastService.remove(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Заказ успешно удален");
    }

    @ExceptionHandler(FastOrderException.class)
    public ResponseEntity<AppErrorDto> handlerException(FastOrderException e){
        AppErrorDto appError = new AppErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessages()
        );
        return new ResponseEntity<>(appError, HttpStatus.BAD_REQUEST);
    }

}
