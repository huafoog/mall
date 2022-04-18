package com.qingshan.mall.pay.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import com.qingshan.mall.pay.entity.WalletEntity;
import com.qingshan.mall.pay.vo.page.WalletVO;
import com.qingshan.mall.pay.service.WalletService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.R;



/**
 * 用户钱包
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
@RestController
@RequestMapping("wallet")
@Api(tags = "用户钱包")
public class WalletController {
    @Autowired
    private WalletService walletService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("用户钱包列表")
    // @RequiresPermissions("pay:wallet:list")
    public R<PageUtils<WalletEntity>> list(@RequestParam WalletVO params){
        PageUtils page = walletService.queryPage(params);

        return R.ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{userId}")
    @ApiOperation("用户钱包信息")
    // @RequiresPermissions("pay:wallet:info")
    public R<WalletEntity> info(@PathVariable("userId") String userId){
		WalletEntity wallet = walletService.getById(userId);

        return R.ok(wallet);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("用户钱包保存")
    // @RequiresPermissions("pay:wallet:save")
    public R save(@RequestBody WalletEntity wallet){
		walletService.save(wallet);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("用户钱包修改")
    // @RequiresPermissions("pay:wallet:update")
    public R update(@RequestBody WalletEntity wallet){
		walletService.updateById(wallet);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("用户钱包删除")
    // @RequiresPermissions("pay:wallet:delete")
    public R delete(@RequestBody String[] userIds){
		walletService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
