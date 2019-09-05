package com.atwzh.sell.service.impl;

import com.atwzh.sell.exception.SellException;
import com.atwzh.sell.dao.ProductInfoDao;
import com.atwzh.sell.dateobject.ProductInfo;
import com.atwzh.sell.dto.CartDTO;
import com.atwzh.sell.enums.ProductStatusEnum;
import com.atwzh.sell.enums.ResultEnum;
import com.atwzh.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangzihang
 * @createTime 2019/6/18
 * @description
 */
@Service
@CacheConfig(cacheNames = "product")
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoDao productInfoDao;

    @Override
    @Cacheable(key = "123")
    public ProductInfo getOne(String productInfoId) {
        return productInfoDao.getOne(productInfoId);
    }

    @Override
    @CachePut(key = "123")
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
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    /**
     * 加库存
     *
     * @param cartDTOList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoDao.findByProductId(cartDTO.getProductId());

            if(productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();

            productInfo.setProductStock(result);

            productInfoDao.save(productInfo);
        }
    }

    /**
     * 减库存
     *
     * @param cartDTOList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoDao.findByProductId(cartDTO.getProductId());

            if(productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();

            if(result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            productInfoDao.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productInfoId) {
        ProductInfo one = productInfoDao.getOne(productInfoId);

        if(one == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
        }
        if(one.getProductStatus().equals(ProductStatusEnum.UP.getCode())){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        one.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoDao.save(one);
    }

    @Override
    public ProductInfo offSale(String productInfoId) {
        ProductInfo one = productInfoDao.getOne(productInfoId);

        if(one == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
        }
        if(one.getProductStatus().equals(ProductStatusEnum.DOWN.getCode())){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        one.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoDao.save(one);
    }
}
