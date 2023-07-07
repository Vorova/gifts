package com.vorova.gifts.controller;

import com.vorova.gifts.mapper.GiftMapper;
import com.vorova.gifts.model.dto.AppErrorDto;
import com.vorova.gifts.model.dto.GiftForUserDto;
import com.vorova.gifts.model.dto.FilterSearchDto;
import com.vorova.gifts.service.abstraction.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/gift")
public class GiftController extends AbstractController{

    private final GiftMapper giftMapper;
    private final GiftService giftService;

    @Autowired
    public GiftController(GiftMapper giftMapper, GiftService giftService) {
        this.giftMapper = giftMapper;
        this.giftService = giftService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        GiftForUserDto dto = giftMapper.toGiftForUserDto(giftService.getById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/search")
    public ResponseEntity<?> getByFilter(@RequestBody FilterSearchDto filterSearchDto) {
        return ResponseEntity.ok(giftService.getByFilter(filterSearchDto));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<AppErrorDto> handlerException(NoSuchElementException e) {
        List<String> errors = new ArrayList<>();
        errors.add("Не существующий элемент");
        AppErrorDto appErrorDto = new AppErrorDto(
                HttpStatus.NOT_FOUND.value(),
                errors
        );
        return new ResponseEntity<>(appErrorDto, HttpStatus.NOT_FOUND);
    }
}