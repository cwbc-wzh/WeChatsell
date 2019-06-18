package com.atwzh.sell.service.impl;

import com.atwzh.sell.dao.ProductInfoDao;
import com.atwzh.sell.dateobject.ProductInfo;
import com.atwzh.sell.enums.ProductStatusEnum;
import com.atwzh.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangzihang
 * @createTime 2019/6/18
 * @description
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoDao productInfoDao;

    @Override
    public ProductInfo getOne(String productInfoId) {
        return productInfoDao.getOne(productInfoId);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    public List<ProductInfo> findByProductStatus(Integer status) {
        return productInfoDao.findByProductStatus(status);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getStatus());
    }
}
