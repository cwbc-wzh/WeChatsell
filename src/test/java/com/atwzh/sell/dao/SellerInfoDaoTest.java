package com.atwzh.sell.dao;

import com.atwzh.sell.dateobject.SellerInfo;
import com.atwzh.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoDaoTest {

    @Autowired
    SellerInfoDao sellerInfoDao;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.generalKey());
        sellerInfo.setUsername("wzh");
        sellerInfo.setPassword("123456");
        sellerInfo.setOpenid("abc");
        SellerInfo save = sellerInfoDao.save(sellerInfo);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByOpenid() {
        String openId = "abc";
        SellerInfo byOpenid = sellerInfoDao.findByOpenid(openId);
        Assert.assertEquals("wzh", byOpenid.getUsername());
    }
}