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
public class CreateGiftDto {
    private String title;
    private String description;
    private String shortDescription;
    private CategoryDto category;
    private double costPrice;
    private double price;
    private List<ImageDto> images;
    private String type;
    private List<TagSearchDto> tags;
    private String tagFor;
}
