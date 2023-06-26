package com.vorova.gifts.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiftForUserDto {
    private long id;
    private String title;
    private String description;
    private String shortDescription;
    private CategoryDto category;
    private double price;
    private List<ImageDto> images;
    private String type;
}
