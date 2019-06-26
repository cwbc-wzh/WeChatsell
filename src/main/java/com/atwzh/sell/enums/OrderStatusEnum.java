package com.atwzh.sell.enums;

import lombok.Getter;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description 订单状态枚举
 */
@Getter
public enum OrderStatusEnum {

    NEW(0, "新下单"),
    FINISH(1, "完结"),
    CANCLE(2, "取消");

    private Integer code;
    private String Meg;

    OrderStatusEnum(Integer code, String meg) {
        this.code = code;
        Meg = meg;
    }
}
