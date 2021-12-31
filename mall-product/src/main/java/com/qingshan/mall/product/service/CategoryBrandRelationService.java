package com.qingshan.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshan.common.utils.PageUtils;
import com.qingshan.mall.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2021-12-28 11:28:06
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

