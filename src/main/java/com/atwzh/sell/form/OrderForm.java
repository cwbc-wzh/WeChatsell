package com.atwzh.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author wangzihang
 * @createTime 2019/7/1
 * @description 表单实体
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "买家姓名必填")
    private String name;

    /**
     * 手机号
     */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "买家地址必填")
    private String address;

    /**
     * 买家微信openid
     */
    @NotEmpty(message = "买家openid必填")
    private String openid;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不为空")
    private String items;
}
