package com.atwzh.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description 专区vo
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> prodcutVOList;
}
