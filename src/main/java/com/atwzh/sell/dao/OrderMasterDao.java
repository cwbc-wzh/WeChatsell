package com.atwzh.sell.dao;

import com.atwzh.sell.dateobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description 订单主表Dao
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster, String> {
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);
}
