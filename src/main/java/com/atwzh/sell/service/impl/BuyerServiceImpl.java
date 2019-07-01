package com.atwzh.sell.service.impl;

import com.atwzh.sell.dto.OrderDto;
import com.atwzh.sell.enums.ResultEnum;
import com.atwzh.sell.exception.SellException;
import com.atwzh.sell.service.BuyerService;
import com.atwzh.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangzihang
 * @createTime 2019/7/1
 * @description
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    OrderService orderService;

    /**
     * 查询买家订单
     *
     * @param openid  买家openid
     * @param orderId 订单id
     * @return 买家订单
     */
    @Override
    public OrderDto findOrderOne(String openid, String orderId) {

        return checkOrderOwner(openid, orderId);
    }



    /**
     * 取消订单
     *
     * @param openid  买家openid
     * @param orderId 订单id
     * @return 取消结果
     */
    @Override
    public OrderDto cancleOrder(String openid, String orderId) {

        OrderDto orderDto = checkOrderOwner(openid, orderId);
        if(orderDto == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancle(orderDto);
    }

    private OrderDto checkOrderOwner(String openid, String orderId) {
        OrderDto result = orderService.findOne(orderId);

        if(result == null) {
            return null;
        }

        if(!result.getBuyerOpenid().equalsIgnoreCase(openid)) {
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return result;
    }
}
