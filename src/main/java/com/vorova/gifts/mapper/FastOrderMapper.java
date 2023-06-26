package com.vorova.gifts.mapper;

import com.vorova.gifts.model.dto.*;
import com.vorova.gifts.model.entity.FastOrder;
import com.vorova.gifts.model.entity.Gift;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FastOrderMapper {

    private final ModelMapper mapper;

    @Autowired
    public FastOrderMapper(ModelMapper modelMapper) {
        this.mapper = modelMapper;

        mapper.createTypeMap(Gift.class, GiftDto.class);
    }

    public FastOrder toFastOrder(CreateFastOrderDto createFastOrderDto) {
        return mapper.map(createFastOrderDto, FastOrder.class);
    }

    public FastOrder toFastOrder(FastOrderDto fastOrderDto) {
        return mapper.map(fastOrderDto, FastOrder.class);
    }

}
