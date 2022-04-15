package com.qingshan.mall.pay.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qingshan.mall.pay.entity.PaymentRecordEntity;
import com.qingshan.mall.pay.service.PaymentRecordService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.R;



/**
 * 支付记录列表
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-15 14:54:47
 */
@RestController
@RequestMapping("pay/paymentrecord")
public class PaymentRecordController {
    @Autowired
    private PaymentRecordService paymentRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("pay:paymentrecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = paymentRecordService.queryPage(params);

        return R.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("pay:paymentrecord:info")
    public R info(@PathVariable("id") String id){
		PaymentRecordEntity paymentRecord = paymentRecordService.getById(id);

        return R.ok(paymentRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("pay:paymentrecord:save")
    public R save(@RequestBody PaymentRecordEntity paymentRecord){
		paymentRecordService.save(paymentRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("pay:paymentrecord:update")
    public R update(@RequestBody PaymentRecordEntity paymentRecord){
		paymentRecordService.updateById(paymentRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("pay:paymentrecord:delete")
    public R delete(@RequestBody String[] ids){
		paymentRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
