package com.atwzh.sell.enums;

import lombok.Getter;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description 返回前段枚举
 */
@Getter
public enum ResultEnum {
    SUCCESS(0,"成功"),
    PARMARTER_ERROR(1, "参数错误"),
    PRODUCT_NOT_EXIT(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存错误"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态错误"),
    ORDER_UPDATE_FAIL(15,"订单更新错误"),
    ORDER_DETAIL_IS_NULL(16, "订单详情为空"),
    PAY_STATUS_ERROR(17,"支付状态错误"),
    CART_EMPTY(18,"购物车为空"),
    ORDER_OWNER_ERROR(19,"订单不属于该用户"),
    WX_MP_ERROE(20, "微信认证相关错误"),
    WXPAY_MONEY_NOTIFY_ERROR(21,"微信异步通知金额不一致"),
    ORDER_CANCLE_SUCCESS(22,"订单取消成功"),
    ORDER_FINISH_SUCCESS(23,"订单完结成功"),
    PRODUCT_STATUS_ERROR(24,"商品状态错误"),
    USERINFO_ERROR(25, "用户身份信息错误"),
    LOGOUT_SUCCESS(26, "登出成功")
    ;




    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
