package com.qingshan.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshan.common.utils.PageUtils;
import com.qingshan.mall.product.entity.CategoryEntity;
import jdk.jfr.Category;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2021-12-28 11:28:06
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> getList();

    void removeMenuByIds(List<Long> catIds);
}

