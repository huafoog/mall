package com.qingshan.mall.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.mall.pay.vo.page.WalletVO;
import com.qingshan.mall.pay.entity.WalletEntity;

import java.util.Map;

/**
 * 用户钱包
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
public interface WalletService extends IService<WalletEntity> {

    PageUtils queryPage(WalletVO params);

    /**
     * 创建用户钱包
     * @param userId
     * @return
     */
    int createWallet(String userId);
}

