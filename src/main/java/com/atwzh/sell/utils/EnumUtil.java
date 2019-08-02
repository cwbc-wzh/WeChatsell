package com.atwzh.sell.utils;

import com.atwzh.sell.enums.CodeEnum;

/**
 * @author wangzihang
 * @createTime 2019/8/1
 * @description
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {

        for(T each : enumClass.getEnumConstants()) {
            if(code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

}
