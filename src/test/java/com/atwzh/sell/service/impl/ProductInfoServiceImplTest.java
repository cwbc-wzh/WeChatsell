package com.atwzh.sell.service.impl;

import com.atwzh.sell.dateobject.ProductInfo;
import com.atwzh.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangzihang
 * @createTime 2019/6/18
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoServiceImpl productInfoService;

    @Test
    public void getOne() {
        ProductInfo one = productInfoService.getOne("123456");
        Assert.assertEquals("123456", one.getProductId());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> all = productInfoService.findAll(pageRequest);
        Assert.assertNotNull(all);
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("0000991");
        productInfo.setProductStock(100);
        productInfo.setProductStatus(0);
        productInfo.setProductPrice(new BigDecimal(1000.0));
        productInfo.setProductName("燕窝");
        productInfo.setProductIcon("http://2.jpg");
        productInfo.setCategoryType(1);
        productInfo.setProductDescription("真贵呀");
        ProductInfo save = productInfoService.save(productInfo);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByProductStatus() {

        List<ProductInfo> byProductStatus = productInfoService.findByProductStatus(ProductStatusEnum.UP.getStatus());
        Assert.assertEquals(ProductStatusEnum.UP.getStatus(), byProductStatus.get(0).getProductStatus());

    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = productInfoService.findUpAll();
        Assert.assertEquals(ProductStatusEnum.UP.getStatus(), upAll.get(0).getProductStatus());
    }
}