package com.atwzh.sell.dao;

import com.atwzh.sell.dateobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangzihang
 * @createTime 2019/5/9
 * @description ProductCategoryDao
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> catagoryIds);

}
