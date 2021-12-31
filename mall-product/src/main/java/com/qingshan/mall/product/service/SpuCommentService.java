package com.qingshan.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshan.common.utils.PageUtils;
import com.qingshan.mall.product.entity.SpuCommentEntity;

import java.util.Map;

/**
 * 商品评价
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2021-12-28 11:28:06
 */
public interface SpuCommentService extends IService<SpuCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

