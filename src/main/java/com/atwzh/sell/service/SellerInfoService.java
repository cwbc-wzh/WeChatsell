package com.atwzh.sell.service;

import com.atwzh.sell.dateobject.SellerInfo;

/**
 * 卖家端service
 */
public interface SellerInfoService {

    public SellerInfo findSellerInfoByOpenId(String openId);

}
