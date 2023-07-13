package com.vorova.gifts.model.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Builder
@Data
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private List<RoleDto> authorities;

}
