package com.atwzh.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description 商品vo
 */
@Data
public class ProductInfoVO implements Serializable {
    /** 789 */
    private static final long serialVersionUID = 1L;
    /** 132*/
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
