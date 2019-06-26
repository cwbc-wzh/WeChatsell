package com.atwzh.sell.dateobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author wangzihang
 * @createTime 2019/6/18
 * @description 商品
 */
@Data
@Entity
public class ProductInfo {

    @Id
    private String productId;

    /** 商品名称 */
    private String productName;

    /** 单价 */
    private BigDecimal productPrice;

    /** 库存 */
    private Integer productStock;

    /** 描述 */
    private String productDescription;

    /** 小图 */
    private String productIcon;

    /** 商品状态 0上架 1下架 */
    private Integer productStatus;

    /** 类目编号 */
    private Integer categoryType;
}
