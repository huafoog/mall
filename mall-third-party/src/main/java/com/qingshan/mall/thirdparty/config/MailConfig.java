package com.qingshan.mall.thirdparty.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮箱
 * @author qingshan
 */
@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class MailConfig {
    private String hostName;
    private String from;
    private String userName;
    private String authKey;

}
