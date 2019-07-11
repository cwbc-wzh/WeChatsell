package com.atwzh.sell.controller;

import com.atwzh.sell.dto.OrderDto;
import com.atwzh.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangzihang
 * @createTime 2019/7/11
 * @description 支付
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    OrderService orderService;

    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl) {

        //1.获取订单
        OrderDto orderDto = orderService.findOne(orderId);

        //发起支付

    }

}
