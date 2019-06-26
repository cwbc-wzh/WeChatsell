package com.atwzh.sell.enums;

import lombok.Getter;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description 支付状态枚举
 */
@Getter
public enum PayStatusEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功");

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
