package com.atwzh.sell.service;

import com.atwzh.sell.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description 订单service
 */
public interface OrderService {

    /** 创建订单 */
    OrderDto create(OrderDto orderDto);
    /** 查询单个订单 */
    OrderDto findOne(String orderId);
    /** 查询订单别表 */
    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);
    /** 取消订单 */
    OrderDto cancle(OrderDto orderDto);
    /** 完结订单 */
    OrderDto finish(OrderDto orderDto);
    /** 支付订单 */
    OrderDto paid(OrderDto orderDto);

}
