package com.atwzh.sell.converter;

import com.atwzh.sell.dateobject.OrderMaster;
import com.atwzh.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzihang
 * @createdate 2019-06-26
 */

public class OrderMaster2OrderDTOConverter {

    public static OrderDto convert(OrderMaster orderMaster) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList) {

        List<OrderDto> orderDtoList = new ArrayList<>();
        OrderDto orderDto = new OrderDto();
        for(OrderMaster orderMaster : orderMasterList) {
            orderDto = convert(orderMaster);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

}
