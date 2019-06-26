package com.atwzh.sell.utils;

import java.util.Random;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description 生成随机数工具类
 */
public class KeyUtil {

    public static synchronized String generalKey() {
        Random random = new Random();
        int number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
