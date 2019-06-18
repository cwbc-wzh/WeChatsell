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

    /**
     * 通过专区类型找专区
     * @param catagoryIds
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> catagoryIds);

}
