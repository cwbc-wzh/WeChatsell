package com.atwzh.sell.enums;

import lombok.Getter;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description 返回前段枚举
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIT(1, "商品不存在")
    ;


    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
