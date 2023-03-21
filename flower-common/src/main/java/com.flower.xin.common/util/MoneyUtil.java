package com.flower.xin.common.util;

public class MoneyUtil {
    public static Float fenToYuan(Integer amount) {
        if(amount == null) {
            return 0F;
        }
        Float amounYuan = amount.floatValue();
        return amounYuan / 100;
    }

    public static Integer yuanToFen(Float amount) {
        Float fenAmount = amount * 100;
        return fenAmount.intValue();
    }
}
