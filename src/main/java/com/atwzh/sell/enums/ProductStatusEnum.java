package com.atwzh.sell.enums;

import lombok.Getter;

/**
 * @author wangzihang
 * @createTime 2019/6/18
 * @description 商品状态
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {

    UP(0,"上架"), DOWN(1, "下架");

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
