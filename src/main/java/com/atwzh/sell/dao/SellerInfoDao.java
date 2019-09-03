package com.atwzh.sell.dao;

import com.atwzh.sell.dateobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoDao extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenid(String OpenId);

}
