package com.qingshan.mall.common.feign.feign.ware;

import com.qingshan.common.core.constant.ProjectNameConstants;
import com.qingshan.common.core.dto.ware.WareSkuInfoDTO;
import com.qingshan.common.core.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 仓库sku服务
 * @author qingshan
 */
@FeignClient(ProjectNameConstants.WARE)
public interface RemoteWareSkuFeignService {

    /**
     * 根据skuId集合获取sku库存
     * @param skuIds
     * @return
     */
    @PostMapping("/getSkuStockBySkuIds")
    R<List<WareSkuInfoDTO>> getSkuStockBySkuIds(@RequestBody List<Long> skuIds);
}
