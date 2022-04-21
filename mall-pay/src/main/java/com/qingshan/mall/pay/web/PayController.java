package com.qingshan.mall.pay.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 收银台
 * @author qs
 */
@Controller
public class PayController {
    @GetMapping("/pay.html")
    public String pay(){
        return "pay";
    }
}
