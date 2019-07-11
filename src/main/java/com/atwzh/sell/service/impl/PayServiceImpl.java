package com.atwzh.sell.service.impl;

import com.atwzh.sell.dto.OrderDto;
import com.atwzh.sell.enums.ResultEnum;
import com.atwzh.sell.exception.SellException;
import com.atwzh.sell.service.OrderService;
import com.atwzh.sell.service.PayService;
import com.atwzh.sell.utils.JsonUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangzihang
 * @createTime 2019/7/11
 * @description 支付
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信订单名字";

    @Autowired
    BestPayServiceImpl bestPayService;
    @Autowired
    OrderService orderService;

    @Override
    public PayResponse create(OrderDto orderDto) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDto.getBuyerOpenid());
        payRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDto.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MWEB);
        log.info("【微信支付】 payRequest = {}", JsonUtil.toJson(payRequest));
        PayResponse response = bestPayService.pay(payRequest);
        log.info("【微信支付】 PayResponse = {}", JsonUtil.toJson(response));
        return response;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1.验证签名
        //2.支付的状态
        //3.支付金额
        //4.支付人（下单人==支付人）
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】 异步通知. payResponse = {}", payResponse);

        OrderDto orderDto = orderService.findOne(payResponse.getOrderId());

        if(orderDto == null) {
            log.error("【微信支付】 订单不存在 orderId = {}", payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if(!orderDto.getOrderAmount().equals(payResponse.getOrderAmount())) {
            log.error("【微信支付】 订单金额不一致 ");
            throw new SellException(ResultEnum.WXPAY_MONEY_NOTIFY_ERROR);
        }

        orderService.paid(orderDto);

        return payResponse;
    }
}
