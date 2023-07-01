package com.vorova.gifts.model.dto;

import com.vorova.gifts.model.entity.Category;
import com.vorova.gifts.model.entity.TagSearch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterSearchDto {

    private String title = null;
    private Category category = null;
    private String type = "gift";
    private int skipResult = 0;
    private int countResult = 10;
    private List<TagSearch> tags = null;
    private double priceMin = 0;
    private double priceMax = 0;
    private String tagFor = "unisex";

}
