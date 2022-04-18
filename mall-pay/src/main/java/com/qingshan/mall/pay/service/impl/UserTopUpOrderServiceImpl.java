package com.qingshan.mall.pay.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.Query;

import com.qingshan.mall.pay.mapper.UserTopUpOrderMapper;
import com.qingshan.mall.pay.entity.UserTopUpOrderEntity;
import com.qingshan.mall.pay.service.UserTopUpOrderService;
import com.qingshan.mall.pay.vo.page.UserTopUpOrderVO;

/**
 * 充值订单
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
@Service("userTopUpOrderService")
public class UserTopUpOrderServiceImpl extends ServiceImpl<UserTopUpOrderMapper, UserTopUpOrderEntity> implements UserTopUpOrderService {

    @Override
    public PageUtils queryPage(UserTopUpOrderVO params) {
        IPage<UserTopUpOrderEntity> page = this.page(
                new Query<UserTopUpOrderEntity>().getPage(params),
                new QueryWrapper<UserTopUpOrderEntity>()
        );

        return new PageUtils(page);
    }

}