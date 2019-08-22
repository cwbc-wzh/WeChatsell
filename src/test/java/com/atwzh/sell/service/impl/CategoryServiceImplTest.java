package com.atwzh.sell.service.impl;

import com.atwzh.sell.dateobject.ProductCategory;
import com.missfresh.antispam.sdk.utils.HttpClientUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangzihang
 * @createTime 2019/6/18
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {

    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = categoryService.findAll();
        Assert.assertNotEquals(0, all.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> byCategoryTypeIn = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3));
        Assert.assertNotEquals(0, byCategoryTypeIn.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("男生专享", 2);
        ProductCategory save = categoryService.save(productCategory);
        Assert.assertEquals(new Integer(2), save.getCategoryType());
    }
}