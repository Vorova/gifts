package com.vorova.gifts.mapper;

import com.vorova.gifts.model.dto.*;
import com.vorova.gifts.model.entity.Category;
import com.vorova.gifts.model.entity.Gift;
import com.vorova.gifts.model.entity.Image;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GiftMapper {

    private final ModelMapper mapper;

    @Autowired
    public GiftMapper(ModelMapper modelMapper) {
        this.mapper = modelMapper;

        mapper.createTypeMap(Image.class, ImageDto.class);
        mapper.createTypeMap(Category.class, CategoryDto.class);
    }

    public Gift toGift(CreateGiftDto createGiftDto) {
        return mapper.map(createGiftDto, Gift.class);
    }

    public Gift toGift(GiftDto giftDto) {
        return mapper.map(giftDto, Gift.class);
    }

    public Gift toGift(Gift gift) {
        return mapper.map(gift, Gift.class);
    }

    public GiftForUserDto toGiftForUserDto(Gift gift) {
        return mapper.map(gift, GiftForUserDto.class);
    }

}