package com.vorova.gifts.mapper;

import com.vorova.gifts.model.dto.RoleDto;
import com.vorova.gifts.model.dto.UserDto;
import com.vorova.gifts.model.entity.Role;
import com.vorova.gifts.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        modelMapper.createTypeMap(Role.class, RoleDto.class);
    }

    public User toUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

}
