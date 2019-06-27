package com.atwzh.sell.enums;

import lombok.Getter;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description 返回前段枚举
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIT(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存错误"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态错误"),
    ORDER_UPDATE_FAIL(15,"订单更新错误"),
    ORDER_DETAIL_IS_NULL(16, "订单详情为空"),
    ;




    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
