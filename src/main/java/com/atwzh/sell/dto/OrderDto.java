package com.atwzh.sell.dto;

import com.atwzh.sell.dateobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description order
 */
@Data
public class OrderDto {

    /** 订单id */
    private String orderId;
    /** 买家名字 */
    private String buyerName;
    /** 买家电话 */
    private String buyerPhone;
    /** 买家地址 */
    private String buyerAddress;
    /** 买家微信openid */
    private String buyerOpenid;
    /** 订单总金额 */
    private BigDecimal orderAmount;
    /** 订单状态 */
    private Integer orderStatus;
    /** 支付状态 */
    private Integer payStatus;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

    private List<OrderDetail> orderDetails;

}
