package com.qingshan.mall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.Query;

import com.qingshan.mall.member.dao.GrowthChangeHistoryDao;
import com.qingshan.mall.member.entity.GrowthChangeHistoryEntity;
import com.qingshan.mall.member.service.GrowthChangeHistoryService;


@Service("growthChangeHistoryService")
public class GrowthChangeHistoryServiceImpl extends ServiceImpl<GrowthChangeHistoryDao, GrowthChangeHistoryEntity> implements GrowthChangeHistoryService {

    @Override
    public IPage<GrowthChangeHistoryEntity> queryPage(Map<String, Object> params) {
        IPage<GrowthChangeHistoryEntity> page = this.page(
                new Query<GrowthChangeHistoryEntity>().getPage(params),
                new QueryWrapper<GrowthChangeHistoryEntity>()
        );

        return page;
    }

}