package com.atwzh.sell.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description 返回给前端类
 */
@Data
public class ResultVO <T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 返回码 */
    private Integer code;
    /** 返回信息 */
    private String msg;
    /** 返回结果集 */
    private T data;
}
