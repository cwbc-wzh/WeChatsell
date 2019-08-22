package com.atwzh.sell.dao;

import com.atwzh.sell.dateobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author wangzihang
 * @createTime 2019/6/18
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    ProductInfoDao productInfoDao;

    @Test
    public void TestSave() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("345678");
        productInfo.setCategoryType(2);
        productInfo.setProductDescription("物美价廉");
        productInfo.setProductIcon("C:/2.jpg");
        productInfo.setProductName("鱼香肉丝11");
        productInfo.setProductPrice(new BigDecimal(5.5));
        productInfo.setProductStatus(0);
        productInfo.setProductStock(100);
        ProductInfo save = productInfoDao.save(productInfo);
        Assert.assertEquals("345678", save.getProductId());
    }

    @Test
    public void TestGetOne() {
        ProductInfo productInfoDaoOne = productInfoDao.getOne("123456");
        Assert.assertEquals("123456", productInfoDaoOne.getProductId());
    }

    @Test
    public void TestFindAll() {
        List<ProductInfo> all = productInfoDao.findAll();
        Assert.assertNotEquals(0, all.size());
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> byProductStatus = productInfoDao.findByProductStatus(0);

        Assert.assertEquals(new Integer(0), byProductStatus.get(0).getProductStatus());
    }
}