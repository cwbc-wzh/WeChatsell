package com.atwzh.sell.dateobject.mapper;

import com.atwzh.sell.dateobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ProductCategoryMapperTest {

    @Autowired
    ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {

        Map<String, Object> map = new HashMap<>();

        map.put("category_name", "午餐必备");
        map.put("category_type", 100);

        int result = mapper.insertByMap(map);

        Assert.assertEquals(1, result);
    }
    @Test
    public void insertByObject() {

        ProductCategory category = new ProductCategory();
        category.setCategoryName("晚餐必备");
        category.setCategoryType(101);
        int result = mapper.insertByObject(category);

        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory productCategory = mapper.findByCategoryType(101);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void updateByCategoryType() {
        int result = mapper.updateByCategoryType("早餐必备", 101);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByObject() {
        ProductCategory category = new ProductCategory();
        category.setCategoryName("晚餐必备");
        category.setCategoryType(101);

        int result = mapper.updateByObject(category);
        Assert.assertEquals(1, result);
    }
}