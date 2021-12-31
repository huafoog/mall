package com.qingshan.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshan.common.utils.PageUtils;
import com.qingshan.mall.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2021-12-28 11:28:06
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

