package com.atwzh.sell.service.impl;

import com.atwzh.sell.dto.OrderDto;
import com.atwzh.sell.service.OrderService;
import com.atwzh.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author wangzihang
 * @createTime 2019/7/11
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    PayServiceImpl payService;

    @Autowired
    OrderService orderService;

    @Test
    public void create() {

        OrderDto orderDto = orderService.findOne("1561597264196168367");
        payService.create(orderDto);

    }
}