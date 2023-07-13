package com.vorova.gifts.controller.admin;

import com.vorova.gifts.controller.AbstractController;
import com.vorova.gifts.exception.GiftException;
import com.vorova.gifts.mapper.GiftMapper;
import com.vorova.gifts.model.dto.AppErrorDto;
import com.vorova.gifts.model.dto.AppResponse;
import com.vorova.gifts.model.dto.CreateGiftDto;
import com.vorova.gifts.model.dto.GiftDto;
import com.vorova.gifts.service.impl.GiftServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/gift")
public class GiftAdminController extends AbstractController {

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
        Long gift_id = giftService.add(giftMapper.toGift(giftDto));
        return ResponseEntity
                .status(201)
                .contentType(MediaType.APPLICATION_JSON)
                .body(AppResponse.response("Успешно создано c id = " + gift_id));
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody GiftDto giftDto) {
        giftService.update(giftMapper.toGift(giftDto));
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(AppResponse.response("Изменения сохранены"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        giftService.remove(id);
        return ResponseEntity
                .status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(AppResponse.response("Сущность удалена"));
    }

    @ExceptionHandler(GiftException.class)
    public ResponseEntity<AppErrorDto> handlerException(GiftException e) {
        AppErrorDto appError = new AppErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessages()
        );
        return new ResponseEntity<>(appError, HttpStatus.BAD_REQUEST);
    }

}