package com.vorova.gifts.service.util;

import com.vorova.gifts.exception.GiftException;
import com.vorova.gifts.model.entity.Gift;
import com.vorova.gifts.model.entity.Image;

public class GiftUtil {

    public static void checkCorrectly(Gift gift) throws GiftException {
        GiftException exception = new GiftException();
        if (!gift.getImages().isEmpty()) {
            boolean flag = false;
            for (Image image : gift.getImages()) {
                if (image.getIsMain()) {
                    if (!flag) {
                        flag = true;
                    } else {
                        exception.addMessage("Только одно изображение может яв-ся основным");
                    }
                }
            }
            if (!flag) {
                gift.getImages().get(0).setIsMain(true);
            }
        }
        if (gift.getTitle() == null || gift.getTitle().strip().equals("")) {
            exception.addMessage("Название сущности не может быть пустым");
        }
        if (!exception.getMessages().isEmpty()) {
            throw exception;
        }
    }

}
