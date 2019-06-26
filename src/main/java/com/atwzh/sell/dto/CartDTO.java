package com.atwzh.sell.dto;

import lombok.Data;

/**
 * 购物车
 * @author wangzihang
 * @createdate 2019-06-26
 */
@Data
public class CartDTO {

    /** 商品id */
    private String productId;
    /** 商品数量 */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
