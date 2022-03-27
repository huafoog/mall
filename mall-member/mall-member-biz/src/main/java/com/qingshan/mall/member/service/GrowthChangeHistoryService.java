package com.qingshan.mall.member.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.mall.member.entity.GrowthChangeHistoryEntity;

import java.util.Map;

/**
 * 成长值变化历史记录
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-03-18 16:22:26
 */
public interface GrowthChangeHistoryService extends IService<GrowthChangeHistoryEntity> {

    IPage<GrowthChangeHistoryEntity> queryPage(Map<String, Object> params);
}

