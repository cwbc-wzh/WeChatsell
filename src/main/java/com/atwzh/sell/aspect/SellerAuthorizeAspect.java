package com.atwzh.sell.aspect;

import com.atwzh.sell.constant.CookieConstant;
import com.atwzh.sell.constant.RedisConstant;
import com.atwzh.sell.exception.SellerAuthorizeException;
import com.atwzh.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.RequestContent;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public * com.atwzh.sell.controller.Seller*.*(..))" +
    "&& !execution(public * com.atwzh.sell.controller.SellerUserController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN_NAME);

        if(cookie == null) {
            throw new SellerAuthorizeException();
        }

        //去redis里查询
        String tokenValue = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PRFIX, cookie.getValue()));

        if(StringUtils.isEmpty(tokenValue)) {
            throw new SellerAuthorizeException();
        }

    }



}
