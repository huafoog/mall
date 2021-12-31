package com.qingshan.mall.coupon.controller;

import com.qingshan.common.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * echo
 * @author qingshan
 */
@RestController
public class EchoController {

    @RequestMapping("/coupon/list")
    public R coupon(){
        return R.ok("123456");
    }

    @GetMapping("/echo/{string}")
    public String echo(@PathVariable String string){
        return string;
    }
}
