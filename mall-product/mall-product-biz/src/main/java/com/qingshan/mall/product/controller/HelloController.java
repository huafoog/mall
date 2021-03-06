package com.qingshan.mall.product.controller;

import com.qingshan.common.core.to.SpuBoundTo;
import com.qingshan.common.core.utils.R;
import com.qingshan.mall.common.feign.feign.coupon.RemoteCouponFeignService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class HelloController {

    private final RemoteCouponFeignService couponFeignService;


    @GetMapping("/hello")
    public String get(){

        return couponFeignService.get();
    }
    @GetMapping("/save")
    public R save(){
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        spuBoundTo.setSpuId(1L);
        spuBoundTo.setBuyBounds(new BigDecimal(1));
        spuBoundTo.setGrowBounds(new BigDecimal(1));
        return  couponFeignService.saveSpuBounds(spuBoundTo);
    }
}
