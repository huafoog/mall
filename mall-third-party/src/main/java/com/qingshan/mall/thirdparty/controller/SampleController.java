package com.qingshan.mall.thirdparty.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @Value("${config.appName}")
    private String appName;
    //2 注解方式
    @GetMapping("/nacos-config-test2")
    public String nacosConfingTest2() {
        return appName;
    }
}
