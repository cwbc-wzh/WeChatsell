package com.atwzh.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "projecturl")
public class ProjectUrlConfig {


    public String weChatMpAuthorize;

    public String weChatOpenAuthorize;

    public String sell;

}
