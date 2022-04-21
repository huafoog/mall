package com.qingshan.mall.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author qingshan
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
//    /**
//     * 视图映射
//     * @param registry
//     */
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        /**
//         *     @GetMapping("/login.html")
//         *     public String loginPage(){
//         *         return "login";
//         *     }
//         */
//        registry.addViewController("/confirm.html").setViewName("confirm");
//    }
}