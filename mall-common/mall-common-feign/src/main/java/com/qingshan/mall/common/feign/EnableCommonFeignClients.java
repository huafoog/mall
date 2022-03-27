package com.qingshan.mall.common.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 *  自定义feign注解 * 添加basePackages路径 *
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
public @interface EnableCommonFeignClients {
    String[] value() default {};
    String[] basePackages() default { "com.qingshan.mall.common.feign" };
    Class<?>[] basePackageClasses() default {};
    Class<?>[] defaultConfiguration() default {};
    Class<?>[] clients() default {};
}