package com.atwzh.sell.dao;

import com.atwzh.sell.BaseTest;
import com.atwzh.sell.dateobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author wangzihang
 * @createTime 2019/5/9
 * @description
 */
public class ProductCategoryDaoTest extends BaseTest {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    public void testGet() {
        List<ProductCategory> productCategory = productCategoryDao.findAll();
        System.out.println(productCategory);
        System.out.println(1);
    }
    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生专区");
        productCategory.setCategoryType(1);
        productCategoryDao.save(productCategory);
    }
    @Test
    public void updateTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(1);
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(1);
        productCategoryDao.save(productCategory);
    }
    @Test
    @Transactional
    public void saveTest2() {
        ProductCategory productCategory = new ProductCategory("热销榜", 3);
        ProductCategory save = productCategoryDao.save(productCategory);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByCategoryIdTest() {
        List<Integer> categoryIds = Arrays.asList(1,2,3,4);
        List<ProductCategory> byCategoryTypeIn = productCategoryDao.findByCategoryTypeIn(categoryIds);
        Assert.assertEquals(2, byCategoryTypeIn.size());

    }

}