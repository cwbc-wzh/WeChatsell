package com.atwzh.sell.service;

import com.atwzh.sell.dto.OrderDto;

/**
 * @author wangzihang
 * @createTime 2019/7/1
 */
public interface BuyerService {

    /**
     * 查询买家订单
     * @param openid 买家openid
     * @param orderId 订单id
     * @return 买家订单
     */
    OrderDto findOrderOne(String openid, String orderId);

    /**
     * 取消订单
     * @param openid 买家openid
     * @param orderId 订单id
     * @return 取消结果
     */
    OrderDto cancleOrder(String openid, String orderId);

}
