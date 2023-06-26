package com.vorova.gifts.model.dto;

import com.vorova.gifts.model.entity.Gift;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFastOrderDto {

    private Gift gift;
    private Gift box;
    private String address;
    private Long tel;
    private String email;
    private String name;

}
