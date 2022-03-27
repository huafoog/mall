package com.qingshan.mall.common.feign.fallback.product;

import com.qingshan.mall.common.feign.fallback.product.impl.RemoteProductFeignServiceImpl;
import com.qingshan.mall.common.feign.feign.product.RemoteProductFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductFallbackFactory implements FallbackFactory<RemoteProductFeignService> {

    @Override
    public RemoteProductFeignService create(Throwable cause) {
        log.error("服务调用失败：{}",cause.getMessage());
        return new RemoteProductFeignServiceImpl();
    }
}
