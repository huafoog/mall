package com.qingshan.mall.pay;

import com.qingshan.mall.common.feign.EnableCommonFeignClients;
import com.qingshan.mall.common.swagger.annotation.EnableCommonSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCommonFeignClients
@EnableCommonSwagger2
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }

}
