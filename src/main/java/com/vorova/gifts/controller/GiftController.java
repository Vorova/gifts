package com.vorova.gifts.controller;

import com.vorova.gifts.mapper.GiftMapper;
import com.vorova.gifts.model.dto.CreateGiftDto;
import com.vorova.gifts.model.entity.Gift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gift")
public class GiftController {

    private final GiftMapper giftMapper;

    @Autowired
    public GiftController(GiftMapper giftMapper) {
        this.giftMapper = giftMapper;
    }

    @GetMapping("{id}")
    public ResponseEntity<CreateGiftDto> getById(@PathVariable Long id) {
        Gift gift = new Gift();

        return null;
    }

}
