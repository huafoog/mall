package com.qingshan.mall.pay.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.Query;

import com.qingshan.mall.pay.mapper.PaymentRecordMapper;
import com.qingshan.mall.pay.entity.PaymentRecordEntity;
import com.qingshan.mall.pay.service.PaymentRecordService;
import com.qingshan.mall.pay.vo.page.PaymentRecordVO;

/**
 * 支付记录列表
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
@Service("paymentRecordService")
public class PaymentRecordServiceImpl extends ServiceImpl<PaymentRecordMapper, PaymentRecordEntity> implements PaymentRecordService {

    @Override
    public PageUtils queryPage(PaymentRecordVO params) {
        IPage<PaymentRecordEntity> page = this.page(
                new Query<PaymentRecordEntity>().getPage(params),
                new QueryWrapper<PaymentRecordEntity>()
        );

        return new PageUtils(page);
    }

}