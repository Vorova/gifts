package com.vorova.gifts.controller;

import com.vorova.gifts.mapper.GiftMapper;
import com.vorova.gifts.model.dto.GiftForUserDto;
import com.vorova.gifts.model.dto.TagSearchDto;
import com.vorova.gifts.service.abstraction.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/gift")
public class GiftController {

    private final GiftMapper giftMapper;

    private final GiftService giftService;

    @Autowired
    public GiftController(GiftMapper giftMapper, GiftService giftService) {
        this.giftMapper = giftMapper;
        this.giftService = giftService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            GiftForUserDto dto = giftMapper.toGiftForUserDto(giftService.getById(id));
            return ResponseEntity.ok(dto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Данного подарка не существует!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Неизвестная ошибка! Обновите страницу!");
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> getByFilter(List<TagSearchDto> tags) {
        try {
            return null;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Неизвестная ошибка! Не удалось получить список подарков");
        }
    }

}