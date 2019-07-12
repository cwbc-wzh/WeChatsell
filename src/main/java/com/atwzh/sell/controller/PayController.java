package com.atwzh.sell.controller;

import com.atwzh.sell.dto.OrderDto;
import com.atwzh.sell.enums.ResultEnum;
import com.atwzh.sell.exception.SellException;
import com.atwzh.sell.service.OrderService;
import com.atwzh.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    PayService payService;

    @RequestMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {

        //1.获取订单
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //发起支付
        PayResponse payResponse = payService.create(orderDto);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        return new ModelAndView("pay/create");
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);

        return new ModelAndView("pay/success");
    }
}
