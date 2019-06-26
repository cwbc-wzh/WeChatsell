package com.atwzh.sell.exception;

import com.atwzh.sell.enums.ResultEnum;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description sellexception
 */
public class SellException extends RuntimeException {

    private Integer code;

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     */
    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
