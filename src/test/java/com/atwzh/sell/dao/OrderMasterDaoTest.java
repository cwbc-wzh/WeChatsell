package com.atwzh.sell.dao;

import com.atwzh.sell.dateobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description OrderMasterTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    OrderMasterDao orderMasterDao;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("12347");
        orderMaster.setBuyerName("wzh");
        orderMaster.setBuyerAddress("杭州");
        orderMaster.setBuyerOpenid("abc123");
        orderMaster.setBuyerPhone("15093753068");
        orderMaster.setOrderAmount(new BigDecimal(202));
        OrderMaster save = orderMasterDao.save(orderMaster);
        Assert.assertEquals(orderMaster.getOrderId(), save.getOrderId());
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderMaster> restlt = orderMasterDao.findByBuyerOpenid("abc123", request);

        Assert.assertEquals(3,restlt.getTotalElements());

    }

    @Test
    public void findOneTest() {
        OrderMaster one = orderMasterDao.findByOrderId("1561597910713209112");
        Assert.assertEquals("1561597910713209112", one.getOrderId());
    }
}