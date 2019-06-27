package com.atwzh.sell.service.impl;

import com.atwzh.sell.converter.OrderDetail2CartDTO;
import com.atwzh.sell.converter.OrderMaster2OrderDTOConverter;
import com.atwzh.sell.exception.SellException;
import com.atwzh.sell.dao.OrderDetailDao;
import com.atwzh.sell.dao.OrderMasterDao;
import com.atwzh.sell.dateobject.OrderDetail;
import com.atwzh.sell.dateobject.OrderMaster;
import com.atwzh.sell.dateobject.ProductInfo;
import com.atwzh.sell.dto.CartDTO;
import com.atwzh.sell.dto.OrderDto;
import com.atwzh.sell.enums.OrderStatusEnum;
import com.atwzh.sell.enums.PayStatusEnum;
import com.atwzh.sell.enums.ResultEnum;
import com.atwzh.sell.service.OrderService;
import com.atwzh.sell.service.ProductInfoService;
import com.atwzh.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wangzihang
 * @createTime 2019/6/26
 * @description
 */
@Service
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
    @Transactional
    public OrderDto create(OrderDto orderDto) {

        String orderId = KeyUtil.generalKey();
        //订单总价
        BigDecimal orderAccount = new BigDecimal(0);

        List<CartDTO> cartDTOList = new ArrayList<>();

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

            BeanUtils.copyProperties(one, orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.generalKey());
            orderDetailDao.save(orderDetail);

            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }

        //3.写入数据库(order_master, order_detail)
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(orderAccount);
        orderMaster.setOrderId(orderId);
        orderMasterDao.save(orderMaster);

        //4.扣除库存
        productInfoService.decreaseStock(cartDTOList);

        return orderDto;
    }

    /**
     * 查询单个订单
     *
     * @param orderId
     */
    @Override
    public OrderDto findOne(String orderId) {

        OrderMaster orderMaster = orderMasterDao.findByOrderId(orderId);

        if(orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetails = orderDetailDao.findByOrderId(orderId);

        if(orderDetails == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetails(orderDetails);
        return orderDto;
    }

    /**
     * 查询订单别表
     *
     * @param buyerOpenid
     * @param pageable
     */
    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasters = orderMasterDao.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderDTOConverter.convert(orderMasters.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<OrderDto>(orderDtoList, pageable, orderMasters.getTotalElements());

        return orderDtoPage;
    }

    /**
     * 取消订单
     *
     * @param orderDto
     */
    @Override
    public OrderDto cancle(OrderDto orderDto) {

        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCLE.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if(updateResult == null) {
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返回库存
        List<OrderDetail> orderDetailList = orderDto.getOrderDetails();
        if(CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_IS_NULL);
        }
        List<CartDTO> cartDTOList = OrderDetail2CartDTO.convert(orderDetailList);
        productInfoService.increaseStock(cartDTOList);
        //如果已支付，需要退款
        if(orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO 暂未写支付相关
        }
        return orderDto;
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
