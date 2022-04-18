package com.qingshan.mall.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.mall.pay.vo.page.UserTopUpOrderVO;
import com.qingshan.mall.pay.entity.UserTopUpOrderEntity;

import java.util.Map;

/**
 * 充值订单
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
public interface UserTopUpOrderService extends IService<UserTopUpOrderEntity> {

    PageUtils queryPage(UserTopUpOrderVO params);
}

