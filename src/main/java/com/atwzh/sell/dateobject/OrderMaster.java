package com.atwzh.sell.dateobject;

import com.atwzh.sell.enums.OrderStatusEnum;
import com.atwzh.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description order_master
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /** 支付状态 */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
