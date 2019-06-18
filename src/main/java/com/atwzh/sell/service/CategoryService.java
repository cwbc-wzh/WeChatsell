package com.atwzh.sell.service;

import com.atwzh.sell.dateobject.ProductCategory;

import java.util.List;

/**
 * @author wangzihang
 * @createTime 2019/6/18
 * @description 类目
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryIdList);

    ProductCategory save(ProductCategory productCategory);

}
