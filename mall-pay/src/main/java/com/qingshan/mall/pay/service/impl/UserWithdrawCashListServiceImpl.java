package com.qingshan.mall.pay.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.Query;

import com.qingshan.mall.pay.mapper.UserWithdrawCashListMapper;
import com.qingshan.mall.pay.entity.UserWithdrawCashListEntity;
import com.qingshan.mall.pay.service.UserWithdrawCashListService;
import com.qingshan.mall.pay.vo.page.UserWithdrawCashListVO;

/**
 * 提现记录表
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
@Service("userWithdrawCashListService")
public class UserWithdrawCashListServiceImpl extends ServiceImpl<UserWithdrawCashListMapper, UserWithdrawCashListEntity> implements UserWithdrawCashListService {

    @Override
    public PageUtils queryPage(UserWithdrawCashListVO params) {
        IPage<UserWithdrawCashListEntity> page = this.page(
                new Query<UserWithdrawCashListEntity>().getPage(params),
                new QueryWrapper<UserWithdrawCashListEntity>()
        );

        return new PageUtils(page);
    }

}