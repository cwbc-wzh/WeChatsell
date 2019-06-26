package com.atwzh.sell.dao;

import com.atwzh.sell.dateobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description 订单详情表Dao
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);
}
