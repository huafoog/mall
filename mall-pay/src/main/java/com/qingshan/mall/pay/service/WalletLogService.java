package com.qingshan.mall.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.mall.pay.vo.page.WalletLogVO;
import com.qingshan.mall.pay.entity.WalletLogEntity;

import java.util.Map;

/**
 * 用户钱包流水记录表
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
public interface WalletLogService extends IService<WalletLogEntity> {

    PageUtils queryPage(WalletLogVO params);
}

