package com.qingshan.common.core.dto.ware;

import lombok.Data;

@Data
public class WareSkuInfoDTO {
    /**
     * 是否有库存
     */
    private Boolean hasStock;

    /**
     * skuId
     */
    private Long SkuId;
}
