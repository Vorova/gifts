package com.vorova.gifts.mapper;

import com.vorova.gifts.model.dto.GiftDto;
import com.vorova.gifts.model.entity.Gift;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GiftMapper {

    private final ModelMapper mapper;

    @Autowired
    public GiftMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public GiftDto toGiftDto(Gift gift) {
        return mapper.map(gift, GiftDto.class);
    }

    public Gift toGift(GiftDto giftDto) {
        return mapper.map(giftDto, Gift.class);
    }

}
