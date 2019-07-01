package com.atwzh.sell.dto;

import com.atwzh.sell.dateobject.OrderDetail;
import com.atwzh.sell.utils.serializer.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /** 更新时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetails;

}
