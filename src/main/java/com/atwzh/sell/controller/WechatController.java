package com.atwzh.sell.controller;

import com.atwzh.sell.enums.ResultEnum;
import com.atwzh.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangzihang
 * @createTime 2019/7/3
 * @description weixin
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {


    @Autowired
    WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        //1.配置
        //2.调用方法
        String url = "http://kmzcaz.natappfree.cc/sell/wechat/usersInfo";
        String result = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, returnUrl);

        return "redirect:" + result;
    }

    @GetMapping("/usersInfo")
    public String usersInfo(@RequestParam("code") String code,
                          @RequestParam("state") String returnUrl) {

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();

        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            e.getError();
            throw new SellException(ResultEnum.WX_MP_ERROE);
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl + "?openId=" + openId;
    }
}
