package com.atwzh.sell.converter;

import com.atwzh.sell.dateobject.OrderMaster;
import com.atwzh.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        List<OrderDto> collect = orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());

        return collect;
    }

}
