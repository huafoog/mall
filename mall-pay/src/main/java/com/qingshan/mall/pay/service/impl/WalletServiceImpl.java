package com.qingshan.mall.pay.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.Query;

import com.qingshan.mall.pay.mapper.WalletMapper;
import com.qingshan.mall.pay.entity.WalletEntity;
import com.qingshan.mall.pay.service.WalletService;


@Service("walletService")
public class WalletServiceImpl extends ServiceImpl<WalletMapper, WalletEntity> implements WalletService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WalletEntity> page = this.page(
                new Query<WalletEntity>().getPage(params),
                new QueryWrapper<WalletEntity>()
        );

        return new PageUtils(page);
    }

}