package com.atwzh.sell.controller;

import com.atwzh.sell.config.ProjectUrlConfig;
import com.atwzh.sell.constant.CookieConstant;
import com.atwzh.sell.constant.RedisConstant;
import com.atwzh.sell.dateobject.SellerInfo;
import com.atwzh.sell.enums.ResultEnum;
import com.atwzh.sell.service.SellerInfoService;
import com.atwzh.sell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户相关
 */
@Controller
@RequestMapping("seller")
public class SellerUserController {

    @Autowired
    SellerInfoService sellerInfoService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    ProjectUrlConfig projectUrlConfig;

    @GetMapping("login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {

        //1.根据openid去数据库匹配
        SellerInfo sellerUserInfo = sellerInfoService.findSellerInfoByOpenId(openid);

        if(sellerUserInfo == null) {
            map.put("msg", ResultEnum.USERINFO_ERROR.getMsg());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        //2.设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PRFIX, token), openid, expire, TimeUnit.SECONDS);


        //3.设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN_NAME, token, expire);
        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String, Object> map) {
        //1.从cookie中查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN_NAME);

        if(cookie != null) {
            //2.清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PRFIX, cookie.getValue()));
            //3.清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN_NAME, null, 0);
        }
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
        map.put("url", "/sell/seller/order/list");

        return new ModelAndView("common/success", map);
    }

}
