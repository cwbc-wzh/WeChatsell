package com.atwzh.sell.converter;

import com.atwzh.sell.dateobject.OrderDetail;
import com.atwzh.sell.dto.OrderDto;
import com.atwzh.sell.enums.ResultEnum;
import com.atwzh.sell.exception.SellException;
import com.atwzh.sell.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzihang
 * @createTime 2019/7/1
 * @description orderForm 转化为 OrderDto
 */
public class OrderForm2OrderDtoConverter {

    public static OrderDto convert(OrderForm orderForm) {

        Gson gson = new Gson();

        OrderDto orderDto = new OrderDto();

        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        orderDto.setBuyerPhone(orderForm.getPhone());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {}.getType());
        } catch (Exception e) {
            throw new SellException(ResultEnum.PARMARTER_ERROR);
        }
        orderDto.setOrderDetails(orderDetailList);

        return orderDto;
    }


}
