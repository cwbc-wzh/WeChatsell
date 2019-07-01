package com.atwzh.sell.controller;

import com.atwzh.sell.converter.OrderForm2OrderDtoConverter;
import com.atwzh.sell.dateobject.OrderDetail;
import com.atwzh.sell.dto.OrderDto;
import com.atwzh.sell.enums.ResultEnum;
import com.atwzh.sell.exception.SellException;
import com.atwzh.sell.form.OrderForm;
import com.atwzh.sell.service.OrderService;
import com.atwzh.sell.utils.ResultVOUtil;
import com.atwzh.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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
    /**
     * 订单列表
     */
    @GetMapping("orderlist")
    public ResultVO<List<OrderDto>> orderList(@RequestParam("openid") String openid,
                                              @RequestParam(name = "page", defaultValue = "0") Integer page,
                                              @RequestParam(name = "size", defaultValue = "10") Integer size) {
        if(StringUtils.isEmpty(openid)) {
            throw new SellException(ResultEnum.PARMARTER_ERROR);
        }

        PageRequest pageRequest = new PageRequest(page,size);

        Page<OrderDto> list = orderService.findList(openid, pageRequest);

        return ResultVOUtil.success(list);
    }

    /**
     * 订单详情
     */
    @GetMapping("orderdetail")
    public ResultVO<OrderDetail> detail(@RequestParam("openid") String openid,
                                        @RequestParam("orderId") String orderId) {

        //TODO 不安全，改进
        OrderDto result = orderService.findOne(orderId);

        return ResultVOUtil.success(result);
    }

    /**
     * 取消订单
     */
    @PostMapping("ordercancle")
    public ResultVO cancle(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {

        //TODO 不安全，改进
        OrderDto result = orderService.findOne(orderId);
        orderService.cancle(result);

        return ResultVOUtil.success();
    }
}
