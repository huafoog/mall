package com.qingshan.mall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author qingshan
 */
@SpringBootApplication
@MapperScan("com.qingshan.mall.product.dao")
public class MallProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallProductApplication.class, args);
    }
}
