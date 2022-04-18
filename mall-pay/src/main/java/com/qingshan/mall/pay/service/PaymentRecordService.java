package com.qingshan.mall.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.mall.pay.vo.page.PaymentRecordVO;
import com.qingshan.mall.pay.entity.PaymentRecordEntity;

import java.util.Map;

/**
 * 支付记录列表
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
public interface PaymentRecordService extends IService<PaymentRecordEntity> {

    PageUtils queryPage(PaymentRecordVO params);
}

