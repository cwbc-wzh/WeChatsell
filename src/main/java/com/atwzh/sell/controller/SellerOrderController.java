package com.atwzh.sell.controller;

import com.atwzh.sell.dto.OrderDto;
import com.atwzh.sell.enums.ResultEnum;
import com.atwzh.sell.exception.SellException;
import com.atwzh.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author wangzihang
 * @createTime 2019/8/1
 * @description
 */
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);

        Page<OrderDto> list = orderService.findList(request);

        map.put("orderDTOPage", list);
        map.put("currentpage", page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }

    @RequestMapping("/cancle")
    public ModelAndView cancle(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {

        try {
            OrderDto orderDto = orderService.findOne(orderId);
            orderService.cancle(orderDto);
        } catch (SellException e) {
            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMsg());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_CANCLE_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");

        return new ModelAndView("common/success",map);
    }

    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDto orderDto = null;
        try {
            orderDto = orderService.findOne(orderId);
        } catch (SellException e) {
            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMsg());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("orderDTO", orderDto);
        return new ModelAndView("order/detail", map);
    }
    @RequestMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDto orderDto = null;
        try {
            orderDto = orderService.findOne(orderId);
            orderService.finish(orderDto);
        } catch (SellException e) {
            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMsg());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
