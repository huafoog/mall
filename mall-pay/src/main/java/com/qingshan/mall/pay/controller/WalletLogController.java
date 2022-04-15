package com.qingshan.mall.pay.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qingshan.mall.pay.entity.WalletLogEntity;
import com.qingshan.mall.pay.service.WalletLogService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.R;



/**
 * 用户钱包流水记录表
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-15 14:54:48
 */
@RestController
@RequestMapping("pay/walletlog")
public class WalletLogController {
    @Autowired
    private WalletLogService walletLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("pay:walletlog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = walletLogService.queryPage(params);

        return R.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("pay:walletlog:info")
    public R info(@PathVariable("id") String id){
		WalletLogEntity walletLog = walletLogService.getById(id);

        return R.ok(walletLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("pay:walletlog:save")
    public R save(@RequestBody WalletLogEntity walletLog){
		walletLogService.save(walletLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("pay:walletlog:update")
    public R update(@RequestBody WalletLogEntity walletLog){
		walletLogService.updateById(walletLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("pay:walletlog:delete")
    public R delete(@RequestBody String[] ids){
		walletLogService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
