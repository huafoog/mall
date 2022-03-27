package com.qingshan.mall.common.feign.fallback.product.impl;

import com.qingshan.common.core.constant.enums.BizCodeEnum;
import com.qingshan.common.core.utils.R;
import com.qingshan.mall.common.feign.feign.product.RemoteProductFeignService;

import java.util.List;

/**
 *
 */
public class RemoteProductFeignServiceImpl implements RemoteProductFeignService {

    @Override
    public R getSkuInfo(Long skuId) {
        return R.failed(BizCodeEnum.SERVICE_DEGRADATION);
    }

    @Override
    public R<List<String>> getSkuSaleAttrValues(Long skuId) {
        return R.failed(BizCodeEnum.SERVICE_DEGRADATION);
    }

    @Override
    public R info(Long skuId) {
        return null;
    }
}
