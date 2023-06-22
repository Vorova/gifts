package com.vorova.gifts.controller.admin;

import com.vorova.gifts.mapper.GiftMapper;
import com.vorova.gifts.model.dto.GiftDto;
import com.vorova.gifts.service.imp.GiftServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/gift")
public class GiftAdminController {

    private final GiftServiceImp giftService;
    private final GiftMapper giftMapper;

    @Autowired
    public GiftAdminController(GiftServiceImp giftService,
                               GiftMapper giftMapper) {
        this.giftService = giftService;
        this.giftMapper = giftMapper;
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody GiftDto giftDto) {
        giftService.add(giftMapper.toGift(giftDto));

        // todo проверка на ошибки и возврат статуса!
        return ResponseEntity.ok("d");
    }

}
