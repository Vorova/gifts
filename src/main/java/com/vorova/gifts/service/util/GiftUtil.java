package com.vorova.gifts.service.util;

import com.vorova.gifts.exception.GiftException;
import com.vorova.gifts.model.entity.Gift;

public class GiftUtil {

    public static void checkCorrectly(Gift gift) throws GiftException {
        // todo только одно изображение может и должно быть основным
        StringBuilder errors = new StringBuilder();
        if (gift.getTitle() == null || gift.getTitle().strip().equals("")) {
            errors.append("Название подарка не может быть пустым. \n");
        }
        if (!errors.isEmpty()) {
            throw new GiftException(errors.toString());
        }
    }

}
