package com.atwzh.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangzihang
 * @createTime 2019/7/3
 * @description
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {

    /**
     * 公共平台
     */
    private String mpAppId;
    private String mpAppSecret;

    /**
     * 开放平台
     */
    private String openAppid;
    private String openAppSecret;

    /**
     * 商户号
     */
    private String mchId;
    /**
     * 商户秘钥
     */
    private String mchKey;
    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 异步通知地址
     */
    private String notifyUrl;

}
