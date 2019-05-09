package com.atwzh.sell.dao;

import com.atwzh.sell.BaseTest;
import com.atwzh.sell.dateobject.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


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
        ProductCategory productCategory = productCategoryDao.getOne(1);
        System.out.println(productCategory);
    }

}