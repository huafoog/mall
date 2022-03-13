package com.qingshan.mall.product.vo.sku;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class ItemSaleAttrVO {
    private Long attrId;
    private String attrName;

    /** AttrValueWithSkuIdVo两个属性 attrValue、skuIds */
    private List<AttrValueWithSkuIdVO> attrValues;
}