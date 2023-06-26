package com.vorova.gifts.service.util;

import com.vorova.gifts.exception.FastOrderException;
import com.vorova.gifts.model.entity.FastOrder;
import com.vorova.gifts.model.entity.Gift;

public class FastOrderUtil {

    public static void checkCorrectly(FastOrder fastOrder) {
        if (fastOrder.getRevenue() < 0) {
            throw new FastOrderException("Себестоимость заказа не может быть больше цены");
        }

        int lengthTel = fastOrder.getTel().toString().length();
        if (lengthTel > 11 || lengthTel < 10) {
            throw new FastOrderException("Некорректный номер телефона");
        }
    }

    public static double getAllCost(FastOrder fastOrder) {
        double costs = 0.0;

        Gift optionalGift = fastOrder.getGift();
        Gift optionalBox = fastOrder.getBox();

        if (optionalGift != null) {
            costs += optionalGift.getCostPrice();
        }
        if (optionalBox != null) {
            costs += optionalBox.getCostPrice();
        }
        return costs;
    }

    public static double getAllPrice(FastOrder fastOrder) {
        double price = 0.0;

        Gift gift = fastOrder.getGift();
        Gift box = fastOrder.getBox();

        if (gift != null) {
            price += gift.getPrice();
        }
        if (box != null) {
            price += box.getPrice();
        }
        return price;
    }

}
