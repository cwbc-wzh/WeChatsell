package com.atwzh.sell.dateobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description order_detail
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {

    @Id
    /** 订单详情id*/
    private String detailId;
    /** 订单id */
    private String orderId;
    /** 商品id */
    private String productId;
    /** 商品名称 */
    private String productName;
    /** 商品价格 */
    private BigDecimal productPrice;
    /** 商品数量 */
    private Integer productQuantity;
    /** 商品小图 */
    private String prodcutIcon;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
