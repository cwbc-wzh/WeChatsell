package com.atwzh.sell.service;

import com.atwzh.sell.dateobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangzihang
 * @createTime 2019/6/18
 * @description 商品
 */
public interface ProductInfoService {

    ProductInfo getOne(String productInfoId);

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    List<ProductInfo> findByProductStatus(Integer status);

    /**
     * 查询所有上架商品列表
     * @return 所有上架商品
     */
    List<ProductInfo> findUpAll();


}
