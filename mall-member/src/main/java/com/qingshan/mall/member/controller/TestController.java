package com.qingshan.mall.member.controller;

import com.qingshan.common.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
public class TestController {
    @GetMapping("/coupon")
    public R getCoupon(){
        return R.ok();
    }
}
