package com.atwzh.sell.service;

import com.atwzh.sell.dto.OrderDto;

/**
 * @author wangzihang
 * @createTime 2019/7/11
 * @description 支付
 */
public interface PayService {

    void create(OrderDto orderDto);

}
