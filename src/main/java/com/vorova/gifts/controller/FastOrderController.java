package com.vorova.gifts.controller;

import com.vorova.gifts.exception.FastOrderException;
import com.vorova.gifts.mapper.FastOrderMapper;
import com.vorova.gifts.model.dto.CreateFastOrderDto;
import com.vorova.gifts.model.dto.FastOrderDto;
import com.vorova.gifts.service.abstraction.FastOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fast-order")
public class FastOrderController {

    private final FastOrderService fastService;
    private final FastOrderMapper fastOrderMapper;

    @Autowired
    public FastOrderController(FastOrderService fastService, FastOrderMapper fastOrderMapper) {
        this.fastService = fastService;
        this.fastOrderMapper = fastOrderMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CreateFastOrderDto fastOrderDto) {
        try {
            fastService.add(fastOrderMapper.toFastOrder(fastOrderDto));
            return ResponseEntity.status(HttpStatus.CREATED).body("Заказ успешно создан");
        } catch (FastOrderException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Не удалось создать быстрый заказ: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Неизвестная ошибка! Не удалось создать быстрый заказ!");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody FastOrderDto fastOrderDto) {
        try {
            fastService.update(fastOrderMapper.toFastOrder(fastOrderDto));
            return ResponseEntity.status(HttpStatus.CREATED).body("Заказ успешно обновлен");
        } catch (FastOrderException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Не удалось обновить быстрый заказ: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Неизвестная ошибка! Не удалось обновить быстрый заказ!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        try {
            fastService.remove(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Заказ успешно удален");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Неизвестная ошибка! Не удалось удалить заказ! Обновите страницу");
        }
    }

}
