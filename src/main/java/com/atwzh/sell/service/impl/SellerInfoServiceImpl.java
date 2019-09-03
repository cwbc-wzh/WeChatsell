package com.atwzh.sell.service.impl;

import com.atwzh.sell.dao.SellerInfoDao;
import com.atwzh.sell.dateobject.SellerInfo;
import com.atwzh.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findSellerInfoByOpenId(String openId) {
        return sellerInfoDao.findByOpenid(openId);
    }
}
