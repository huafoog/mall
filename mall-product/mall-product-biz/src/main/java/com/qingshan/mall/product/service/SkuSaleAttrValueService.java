package com.qingshan.mall.product.service;

import com.qingshan.common.utils.PageUtils;
import com.qingshan.mall.product.entity.SkuSaleAttrValueEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshan.mall.product.vo.sku.SkuItemSaleAttrVO;

import java.util.List;
import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuItemSaleAttrVO> getSaleAttrBySpuId(Long spuId);

}

