package com.qingshan.mall.auth;

import com.qingshan.mall.common.feign.EnableCommonFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//@EnableRedisHttpSession
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = { "com.qingshan.mall.auth","com.qingshan.common.core.exception" } )
@EnableCommonFeignClients
public class AuthApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =SpringApplication.run(AuthApplication.class, args);
    }

}
