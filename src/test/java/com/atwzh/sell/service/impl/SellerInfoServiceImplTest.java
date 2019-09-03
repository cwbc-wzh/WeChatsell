package com.atwzh.sell.service.impl;

import com.atwzh.sell.dateobject.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoServiceImplTest {

    @Autowired
    SellerInfoServiceImpl sellerInfoService;
    @Test
    public void findSellInfoByOpenId() {

        String openId = "abc";

        SellerInfo sellerInfoByOpenId = sellerInfoService.findSellerInfoByOpenId(openId);

        Assert.assertEquals("abc", sellerInfoByOpenId.getOpenid());

    }

}