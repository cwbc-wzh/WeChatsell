package com.atwzh.sell.service.impl;

import com.atwzh.sell.dao.ProductCategoryDao;
import com.atwzh.sell.dateobject.ProductCategory;
import com.atwzh.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangzihang
 * @createTime 2019/6/18
 * @description 类目
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryDao.getOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryIdList) {
        return productCategoryDao.findByCategoryTypeIn(categoryIdList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }
}
