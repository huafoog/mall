package com.qingshan.mall.coupon.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloController {


    @GetMapping("/hello")
    public String get(){
        return "1234456";
    }
}
