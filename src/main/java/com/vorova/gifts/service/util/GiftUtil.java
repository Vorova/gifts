package com.vorova.gifts.service.util;

import com.vorova.gifts.exception.GiftException;
import com.vorova.gifts.model.entity.Gift;
import com.vorova.gifts.model.entity.Image;

public class GiftUtil {

    public static void checkCorrectly(Gift gift) throws GiftException {
        boolean flag = false;
        for (Image image : gift.getImages()) {
            if (image.getIsMain()) {
                if (!flag) {
                    flag = true;
                } else {
                    throw new GiftException("Только одно изображение может быть основным");
                }
            }
        }
        StringBuilder errors = new StringBuilder();
        if (gift.getTitle() == null || gift.getTitle().strip().equals("")) {
            errors.append("Название подарка не может быть пустым. \n");
        }
        if (!errors.isEmpty()) {
            throw new GiftException(errors.toString());
        }
    }

}