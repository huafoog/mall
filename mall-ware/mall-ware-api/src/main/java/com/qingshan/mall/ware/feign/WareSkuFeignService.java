package com.qingshan.mall.ware.feign;

import com.qingshan.common.constant.ProjectNameConstants;
import com.qingshan.common.utils.R;
import com.qingshan.mall.ware.dto.WareSkuInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 仓库sku服务
 * @author qingshan
 */
@FeignClient(ProjectNameConstants.WARE)
public interface WareSkuFeignService {

    /**
     * 根据skuId集合获取sku库存
     * @param skuIds
     * @return
     */
    @PostMapping("/getSkuStockBySkuIds")
    R<List<WareSkuInfoDTO>> getSkuStockBySkuIds(@RequestBody List<Long> skuIds);
}
