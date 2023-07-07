package com.vorova.gifts.model.dto;

import com.vorova.gifts.model.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id = null;
    private String username;
    private String password;
    private List<RoleDto> roles;

}
