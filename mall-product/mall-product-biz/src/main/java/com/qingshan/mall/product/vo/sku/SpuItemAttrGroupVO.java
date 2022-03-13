package com.qingshan.mall.product.vo.sku;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class SpuItemAttrGroupVO {
    private String groupName;

    /** 两个属性attrName、attrValue */
    private List<SpuBaseAttrVO> attrs;
}
