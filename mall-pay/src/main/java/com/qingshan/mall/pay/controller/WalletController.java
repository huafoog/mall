package com.qingshan.mall.pay.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qingshan.mall.pay.entity.WalletEntity;
import com.qingshan.mall.pay.service.WalletService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.R;



/**
 * 用户钱包
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-15 14:54:48
 */
@RestController
@RequestMapping("pay/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("pay:wallet:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = walletService.queryPage(params);

        return R.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    // @RequiresPermissions("pay:wallet:info")
    public R info(@PathVariable("userId") String userId){
		WalletEntity wallet = walletService.getById(userId);

        return R.ok(wallet);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("pay:wallet:save")
    public R save(@RequestBody WalletEntity wallet){
		walletService.save(wallet);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("pay:wallet:update")
    public R update(@RequestBody WalletEntity wallet){
		walletService.updateById(wallet);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("pay:wallet:delete")
    public R delete(@RequestBody String[] userIds){
		walletService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
