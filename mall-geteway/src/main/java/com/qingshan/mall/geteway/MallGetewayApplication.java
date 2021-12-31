package com.qingshan.mall.geteway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * 网关
 * @author qingshan
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MallGetewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallGetewayApplication.class, args);
    }

}
