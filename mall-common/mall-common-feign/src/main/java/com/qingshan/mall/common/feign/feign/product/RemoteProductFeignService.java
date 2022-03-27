package com.qingshan.mall.common.feign.feign.product;


import com.qingshan.common.core.constant.ProjectNameConstants;
import com.qingshan.common.core.dto.product.sku.SkuInfoDTO;
import com.qingshan.common.core.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(ProjectNameConstants.PRODUCT)
public interface RemoteProductFeignService {
    @RequestMapping("/product/skuinfo/info/{skuId}")
    R<SkuInfoDTO> getSkuInfo(@PathVariable("skuId") Long skuId);

    @GetMapping("product/skusaleattrvalue/stringlist/{skuId}")
    R<List<String>> getSkuSaleAttrValues(@PathVariable("skuId") Long skuId);

    /**
     *      /product/skuinfo/info/{skuId}
     *
     *
     *   1)、让所有请求过网关；
     *          1、@FeignClient("gulimall-gateway")：给gulimall-gateway所在的机器发请求
     *          2、/api/product/skuinfo/info/{skuId}
     *   2）、直接让后台指定服务处理
     *          1、@FeignClient("gulimall-gateway")
     *          2、/product/skuinfo/info/{skuId}
     *
     * @return
     */
    @RequestMapping("/product/skuinfo/info/{skuId}")
    R<SkuInfoDTO> info(@PathVariable("skuId") Long skuId);
}
