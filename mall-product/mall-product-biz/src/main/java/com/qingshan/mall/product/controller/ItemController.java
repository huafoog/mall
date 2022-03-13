package com.qingshan.mall.product.controller;

import com.qingshan.mall.product.service.SkuInfoService;
import com.qingshan.mall.product.vo.sku.SkuItemVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class ItemController {

    private final SkuInfoService skuInfoService;

    @GetMapping("/{skuId}.html")
    public String item(@PathVariable("skuId") String skuId, Model model){
        SkuItemVO vo = skuInfoService.item(skuId);

            model.addAttribute("item", vo);
            return "item";
    }
}
