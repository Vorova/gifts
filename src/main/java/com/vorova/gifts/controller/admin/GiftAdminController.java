package com.vorova.gifts.controller.admin;

import com.vorova.gifts.exception.GiftException;
import com.vorova.gifts.mapper.GiftMapper;
import com.vorova.gifts.model.dto.CreateGiftDto;
import com.vorova.gifts.model.dto.GiftDto;
import com.vorova.gifts.service.imp.GiftServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> add(@RequestBody CreateGiftDto giftDto) {
        try {
            Long gift_id = giftService.add(giftMapper.toGift(giftDto));
            return ResponseEntity.status(201).body("Подарок успешно добавлен c id = " + gift_id);
        } catch (GiftException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Не удалось добавить подарок по причине(ам):" + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(e.getMessage());
                    .body("Неизвестная ошибка! Не удалось добавить подарок");
        }
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody GiftDto giftDto) {
        try {
            giftService.update(giftMapper.toGift(giftDto));
            return ResponseEntity.status(200).body("Изменения сохранены");
        } catch (GiftException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Изменения не сохранены по причине(ам):" + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        try {
            giftService.remove(id);
            return ResponseEntity.status(200).body("Подарок удален");
        } catch (GiftException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
