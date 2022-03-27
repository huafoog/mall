package com.qingshan.mall.cart.config;

import com.qingshan.mall.cart.interceptor.CartInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 */
@Configuration
public class MallWebConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器的配置，不能只把拦截器加入容器中，不然拦截器不生效的
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CartInterceptor()).addPathPatterns("/**");
    }
}
