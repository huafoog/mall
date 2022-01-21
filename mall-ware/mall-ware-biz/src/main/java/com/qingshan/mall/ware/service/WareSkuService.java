package com.qingshan.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshan.common.utils.PageUtils;
import com.qingshan.mall.ware.dto.WareSkuInfoDTO;
import com.qingshan.mall.ware.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:59:40
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);


    /**
     * 根据skuid集合获取sku库存
     * @param skuIds
     * @return
     */
    List<WareSkuInfoDTO> getSkuStockBySkuIds(List<Long> skuIds);
}

