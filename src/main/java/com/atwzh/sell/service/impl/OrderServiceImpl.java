package com.atwzh.sell.service.impl;

import com.atwzh.exception.SellException;
import com.atwzh.sell.dao.OrderDetailDao;
import com.atwzh.sell.dao.OrderMasterDao;
import com.atwzh.sell.dateobject.OrderDetail;
import com.atwzh.sell.dateobject.OrderMaster;
import com.atwzh.sell.dateobject.ProductInfo;
import com.atwzh.sell.dto.OrderDto;
import com.atwzh.sell.enums.ResultEnum;
import com.atwzh.sell.service.OrderService;
import com.atwzh.sell.service.ProductInfoService;
import com.atwzh.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description
 */
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    OrderDetailDao orderDetailDao;
    @Autowired
    OrderMasterDao orderMasterDao;


    /**
     * 创建订单
     *
     * @param orderDto
     */
    @Override
    public OrderDto create(OrderDto orderDto) {

        String orderId = KeyUtil.generalKey();
        //订单总价
        BigDecimal orderAccount = new BigDecimal(0);

        //1.查询商品(数量，价格)
        for(OrderDetail orderDetail : orderDto.getOrderDetails()) {
            ProductInfo one = productInfoService.getOne(orderDetail.getProductId());
            if(one == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            //2.计算总价
            orderAccount = one.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAccount);

            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.generalKey());
            BeanUtils.copyProperties(one, orderDetail);
            orderDetailDao.save(orderDetail);
        }
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderAmount(orderAccount);
        orderMaster.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMasterDao.save(orderMaster);

        //3.写入数据库(order_master, order_detail)

        //4.扣除库存

        return null;
    }

    /**
     * 查询单个订单
     *
     * @param orderId
     */
    @Override
    public OrderDto findOne(String orderId) {
        return null;
    }

    /**
     * 查询订单别表
     *
     * @param buyerOpenid
     * @param pageable
     */
    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    /**
     * 取消订单
     *
     * @param orderDto
     */
    @Override
    public OrderDto cancle(OrderDto orderDto) {
        return null;
    }

    /**
     * 完结订单
     *
     * @param orderDto
     */
    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    /**
     * 支付订单
     *
     * @param orderDto
     */
    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
