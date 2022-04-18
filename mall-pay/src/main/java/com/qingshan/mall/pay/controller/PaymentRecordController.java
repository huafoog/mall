package com.qingshan.mall.pay.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import com.qingshan.mall.pay.entity.PaymentRecordEntity;
import com.qingshan.mall.pay.vo.page.PaymentRecordVO;
import com.qingshan.mall.pay.service.PaymentRecordService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.R;



/**
 * 支付记录列表
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
@RestController
@RequestMapping("paymentrecord")
@Api(tags = "支付记录列表")
public class PaymentRecordController {
    @Autowired
    private PaymentRecordService paymentRecordService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("支付记录列表列表")
    // @RequiresPermissions("pay:paymentrecord:list")
    public R<PageUtils<PaymentRecordEntity>> list(@RequestParam PaymentRecordVO params){
        PageUtils page = paymentRecordService.queryPage(params);

        return R.ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("支付记录列表信息")
    // @RequiresPermissions("pay:paymentrecord:info")
    public R<PaymentRecordEntity> info(@PathVariable("id") String id){
		PaymentRecordEntity paymentRecord = paymentRecordService.getById(id);

        return R.ok(paymentRecord);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("支付记录列表保存")
    // @RequiresPermissions("pay:paymentrecord:save")
    public R save(@RequestBody PaymentRecordEntity paymentRecord){
		paymentRecordService.save(paymentRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("支付记录列表修改")
    // @RequiresPermissions("pay:paymentrecord:update")
    public R update(@RequestBody PaymentRecordEntity paymentRecord){
		paymentRecordService.updateById(paymentRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("支付记录列表删除")
    // @RequiresPermissions("pay:paymentrecord:delete")
    public R delete(@RequestBody String[] ids){
		paymentRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
