package com.qingshan.mall.product.app;

import com.qingshan.common.core.utils.R;
import com.qingshan.mall.product.service.SkuSaleAttrValueService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skusaleattrvalue")
@AllArgsConstructor
public class AppSkuSaleAttrValueController {
    private final SkuSaleAttrValueService skuSaleAttrValueService;

    @GetMapping("stringlist/{skuId}")
    public R<List<String>> getSkuSaleAttrValues(@PathVariable("skuId") Long skuId){
        return R.ok(skuSaleAttrValueService.getSkuSaleAttrValuesAsStringList(skuId));
    }
}
