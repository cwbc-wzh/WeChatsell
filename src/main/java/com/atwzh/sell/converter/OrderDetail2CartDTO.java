package com.atwzh.sell.converter;

import com.atwzh.sell.dateobject.OrderDetail;
import com.atwzh.sell.dto.CartDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzihang
 * @createTime 2019/6/27
 */
public class OrderDetail2CartDTO {

    public static CartDTO convert(OrderDetail orderDetail) {
        CartDTO cartDTO = new CartDTO();
        BeanUtils.copyProperties(orderDetail, cartDTO);
        return cartDTO;
    }


    public static List<CartDTO> convert(List<OrderDetail> orderDetailList) {
        List<CartDTO> cartDTOList = new ArrayList<>();

        for(OrderDetail orderDetail : orderDetailList) {
            CartDTO cartDTO = new CartDTO();
            BeanUtils.copyProperties(orderDetail, cartDTO);
            cartDTOList.add(cartDTO);
        }
        return cartDTOList;
    }
}
