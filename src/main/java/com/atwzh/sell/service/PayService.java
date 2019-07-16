package com.atwzh.sell.service;

import com.atwzh.sell.dto.OrderDto;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * @author wangzihang
 * @createTime 2019/7/11
 * @description 支付
 */
public interface PayService {

    PayResponse create(OrderDto orderDto);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDto orderDto);
}
