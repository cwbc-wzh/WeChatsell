package com.atwzh.sell.enums;

import lombok.Getter;

/**
 * @author wangzihang
 * @createTime 2019/6/18
 * @description 商品状态
 */
@Getter
public enum  ProductStatusEnum {

    UP(0,"上架"), DOWN(1, "下架");

    private Integer status;
    private String message;

    ProductStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
