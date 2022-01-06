package com.qingshan.mall.thirdparty.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * oss配置
 * @author qingshan
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "aliyun.oss")
@Data
public class OssConfig {
    private String endpoint;
    private String keyId;
    private String keySecret;
    private String bucketName;
    private String bucketHost;
}
