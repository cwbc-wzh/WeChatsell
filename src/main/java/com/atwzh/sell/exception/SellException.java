package com.atwzh.sell.exception;

import com.atwzh.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description sellexception
 */
@Getter
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

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
