package com.atwzh.sell.dao;

import com.atwzh.sell.dateobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangzihang
 * @createTime 2019/6/18
 * @description
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer status);

}
