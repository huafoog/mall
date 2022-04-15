package com.qingshan.mall.pay.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.Query;

import com.qingshan.mall.pay.mapper.WalletLogMapper;
import com.qingshan.mall.pay.entity.WalletLogEntity;
import com.qingshan.mall.pay.service.WalletLogService;


@Service("walletLogService")
public class WalletLogServiceImpl extends ServiceImpl<WalletLogMapper, WalletLogEntity> implements WalletLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WalletLogEntity> page = this.page(
                new Query<WalletLogEntity>().getPage(params),
                new QueryWrapper<WalletLogEntity>()
        );

        return new PageUtils(page);
    }

}