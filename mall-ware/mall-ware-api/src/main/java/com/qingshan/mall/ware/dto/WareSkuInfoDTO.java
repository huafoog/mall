package com.qingshan.mall.ware.dto;

import lombok.Data;

import java.math.BigDecimal;

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
