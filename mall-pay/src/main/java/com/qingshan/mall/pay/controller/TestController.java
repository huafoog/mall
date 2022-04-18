package com.qingshan.mall.pay.controller;


import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "描述")
public class TestController {
    @GetMapping("/")
    @ApiOperation("获取")

    public String get(){
        return  "123";
    }
}
