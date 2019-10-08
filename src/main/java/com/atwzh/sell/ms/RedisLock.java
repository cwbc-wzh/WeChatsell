package com.atwzh.sell.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 用redis实现分布式锁
 */
@Component
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;
    //加锁
    public boolean lock1(String key, String value) {
        //setIfAbsent相当于jedis中的setnx，如果能赋值就返回true，如果已经有值了，就返回false
        //即：在判断这个key是不是第一次进入这个方法
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            //第一次，即：这个key还没有被赋值的时候
            return true;
        }
        return false;
    }

    public boolean lock3(String key, String value) {
        //setIfAbsent相当于jedis中的setnx，如果能赋值就返回true，如果已经有值了，就返回false
        //即：在判断这个key是不是第一次进入这个方法
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            //第一次，即：这个key还没有被赋值的时候
            return true;
        }
        String current_value = redisTemplate.opsForValue().get(key);
        if (!current_value.toString().equals("")
                //超时了
                && Long.parseLong(current_value) < System.currentTimeMillis()) {//①
            String old_value = redisTemplate.opsForValue().getAndSet(key, value);//②
            if (!old_value.toString().equals("")
                    && old_value.equals(current_value)) {
                return true;
            }
        }
        return false;
    }

    //解锁
    public void unlock(String key, String value) {
        try {
            if (redisTemplate.opsForValue().get(key).toString().equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
