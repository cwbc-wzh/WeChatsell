package com.atwzh.sell.controller;

import com.atwzh.sell.converter.OrderForm2OrderDtoConverter;
import com.atwzh.sell.dto.OrderDto;
import com.atwzh.sell.enums.ResultEnum;
import com.atwzh.sell.exception.SellException;
import com.atwzh.sell.form.OrderForm;
import com.atwzh.sell.service.OrderService;
import com.atwzh.sell.utils.ResultVOUtil;
import com.atwzh.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzihang
 * @createTime 2019/7/1
 * @description 买家订单api
 */
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new SellException(ResultEnum.PARMARTER_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDto orderDto = OrderForm2OrderDtoConverter.convert(orderForm);

        if(CollectionUtils.isEmpty(orderDto.getOrderDetails())) {
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDto dto = orderService.create(orderDto);

        Map<String, String> map = new HashMap<>();

        map.put("orderId", dto.getOrderId());

        return ResultVOUtil.success(map);
    }
    //订单列表

    //订单详情

    //取消订单
}
