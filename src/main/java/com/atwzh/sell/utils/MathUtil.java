package com.atwzh.sell.utils;

/**
 * @author wangzihang
 * @createTime 2019/7/12
 * @description
 */
public class MathUtil {

    private static final Double MONEY_MIN = 0.01;

    public static Boolean equal(Double d1, Double d2) {

        double abs = Math.abs(d1 - d2);

        if(abs < MONEY_MIN) {
            return true;
        } else {
            return false;
        }

    }

}
