package com.vorova.gifts.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiftDto {
    private long id;
    private String name;
    private String description;
    private String category;
    private double price;
}
