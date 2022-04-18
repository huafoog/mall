package com.qingshan.mall.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.mall.pay.vo.page.UserWithdrawCashListVO;
import com.qingshan.mall.pay.entity.UserWithdrawCashListEntity;

import java.util.Map;

/**
 * 提现记录表
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
public interface UserWithdrawCashListService extends IService<UserWithdrawCashListEntity> {

    PageUtils queryPage(UserWithdrawCashListVO params);
}

