package com.qingshan.mall.product.service;

import com.qingshan.common.utils.PageUtils;
import com.qingshan.mall.product.entity.ProductAttrValueEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshan.mall.product.vo.sku.SpuItemAttrGroupVO;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveProductAttr(List<ProductAttrValueEntity> collect);


    List<ProductAttrValueEntity> baseAttrlistforspu(Long spuId);


    void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities);
}

