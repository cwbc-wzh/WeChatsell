package com.atwzh.sell.ms;

import com.atwzh.sell.exception.CongestionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class MsService {

    @Autowired
    private RedisLock redisLock;
    //商品详情
    private static HashMap<String, Integer> product = new HashMap();
    //订单表
    private static HashMap<String, String> orders = new HashMap();
    //库存表
    private static HashMap<String, Integer> stock = new HashMap();

    static {
        product.put("123", 10000);
        stock.put("123", 10000);
    }

    public String select_info(String product_id) {
        return "限量抢购商品XXX共" + product.get(product_id) + ",现在成功下单" + orders.size()
                + ",剩余库存" + stock.get(product_id) + "件";
    }

    /**
     * 下单
     *
     * @param product_id
     * @return
     */
    public String order1(String product_id) {
        if (stock.get(product_id) == 0) {
            return "活动已经结束了";
            //已近买完了
        } else {
            //还没有卖完
            try {
                //模拟操作数据库
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            orders.put(UUID.randomUUID().toString(), product_id);
            stock.put(product_id, stock.get(product_id) - 1);
        }
        return select_info(product_id);
    }

    /**
     * 高并发没问题，效率还行
     *
     * @param product_id
     * @return
     */
    public String order3(String product_id) throws CongestionException {
        /**
         * redis加锁
         */
        String value = System.currentTimeMillis() + 10000 + "";
        if (!redisLock.lock3(product_id, value)) {
            //系统繁忙，请稍后再试
            throw new CongestionException();
        }
        //##############################业务逻辑#################################//
        if (stock.get(product_id) == 0) {
            return "活动已经结束了";
            //已近买完了
        } else {
            //还没有卖完
            try {
                //模拟操作数据库
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            orders.put(UUID.randomUUID().toString(), product_id);
            stock.put(product_id, stock.get(product_id) - 1);
        }
        //##############################业务逻辑#################################//
        /**
         * redis解锁
         */
        redisLock.unlock(product_id, value);
        return select_info(product_id);
    }

}
