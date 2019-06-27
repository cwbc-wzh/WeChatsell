package com.atwzh.sell.dao;

import com.atwzh.sell.dateobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    OrderDetailDao orderDetailDao;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("12345");
        orderDetail.setDetailId("123457");
        orderDetail.setProductIcon("http://1.jpg");
        orderDetail.setProductId("234567");
        orderDetail.setProductName("鱼香肉丝");
        orderDetail.setProductPrice(new BigDecimal(5.5));
        orderDetail.setProductQuantity(5);
        OrderDetail save = orderDetailDao.save(orderDetail);
        Assert.assertEquals(orderDetail.getDetailId(), save.getDetailId());
    }

    @Test
    public void findByOrderId() {

        List<OrderDetail> byOrderId = orderDetailDao.findByOrderId("1561597910713209112");
        Assert.assertNotNull(byOrderId);
    }
}