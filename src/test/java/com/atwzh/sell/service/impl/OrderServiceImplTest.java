package com.atwzh.sell.service.impl;

import com.atwzh.sell.dateobject.OrderDetail;
import com.atwzh.sell.dateobject.OrderMaster;
import com.atwzh.sell.dto.OrderDto;
import com.atwzh.sell.enums.OrderStatusEnum;
import com.atwzh.sell.enums.PayStatusEnum;
import com.sun.xml.internal.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "111220";
    private final String PRODUCT_ID = "234567";
    @Test
    public void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerAddress("杭州");
        orderDto.setBuyerName("wzh");
        orderDto.setBuyerOpenid(BUYER_OPENID);
        orderDto.setBuyerPhone("15093753068");

        List<OrderDetail> orderDetails = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId(PRODUCT_ID);
        orderDetail.setProductQuantity(3);
        orderDetails.add(orderDetail);

        orderDto.setOrderDetails(orderDetails);

        OrderDto orderDto1 = orderService.create(orderDto);

        Assert.assertNotNull(orderDto1);
    }

    @Test
    public void findOne() {

        OrderDto orderDto = orderService.findOne("1561597910713209112");

        Assert.assertNotNull(orderDto);
    }

    @Test
    public void findList() {

        PageRequest pageRequest = new PageRequest(0, 1);

        Page<OrderDto> orderDtos = orderService.findList(BUYER_OPENID, pageRequest);

        Assert.assertNotEquals(0, orderDtos.getTotalElements());

    }

    @Test
    public void cancle() {

        OrderDto orderDto = orderService.findOne("1561597910713209112");

        OrderDto orderDto1 = orderService.cancle(orderDto);

        Assert.assertEquals(OrderStatusEnum.CANCLE.getCode(), orderDto1.getOrderStatus());
    }

    @Test
    public void finish() {

        OrderDto orderDto = orderService.findOne("1561597910713209112");

        OrderDto orderDto1 = orderService.finish(orderDto);

        Assert.assertEquals(OrderStatusEnum.FINISH.getCode(), orderDto1.getOrderStatus());

    }

    @Test
    public void paid() {

        OrderDto orderDto = orderService.findOne("1561597910713209112");

        OrderDto orderDto1 = orderService.paid(orderDto);

        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), orderDto1.getPayStatus());

    }
}